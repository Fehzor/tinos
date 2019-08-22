/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.main;

import static bot.Launcher.client;
import bot.command.CommandParser;
import data.UserData;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.EventDispatcher;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Channel;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import discord4j.core.object.entity.TextChannel;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.object.util.Snowflake;
import reactor.core.publisher.Flux;

/**
 *
 * @author FF6EB
 */
public class IO extends Thread{
    public DiscordClient client;
    
    private String joinMessage = "Hello and welcome to Tinos Ranchs Discord Channel!\n\n"
            + ""
            + "To get started, type setname <name> and setguild <guild> to set your knight name and guild!\n"
            + "(This must be done in one of the main chat channels and not the DM!)\n\n"
            + ""
            + "This will not nickname you, but will help people know your in game name via the info <mention> command!";
    
    public IO(String arg){
        client = new DiscordClientBuilder(arg).build();
        
        this.start();
    }
    
    public void run(){
        client.getEventDispatcher().on(ReadyEvent.class)
        .subscribe(ready -> init(ready));
        
        client.getEventDispatcher()
                .on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .subscribe(mess -> listen(mess));
        
        client.getEventDispatcher()
                .on(MemberJoinEvent.class)
                .map(MemberJoinEvent::getMember)
                .subscribe(u -> send(joinMessage,u));
                
        
        client.login().block();
    }
    
    private void init(ReadyEvent ready){
        System.out.println("INITIALIZING IO");
    }
    
    private void listen(Message mess){
        try{
            addMessage(mess);
        } catch (Exception E){
            System.err.println("FAILURE TO ADD MESSAGE!");
        }
        
        if(mess.getAuthor().get().isBot()){
            //If it's a bot, we don't listen to what it tells us.
            return;
        }
        
        try{
            String channelName = ((TextChannel)mess.getChannel().block()).getName();
            String userName = mess.getAuthor().get().getUsername();

            System.out.println(channelName+"/"+userName+"> "+mess.getContent().get());
            
            CommandParser.eval(this, mess);
        } catch (Exception E){
            E.printStackTrace();
            System.err.println("BAD MESSAGE (Probably a picture rofl)");
        }
    }
    
    public void addMessage(Message mess){
        UserData UD = UserData.getUD(mess.getAuthor().get());
        
        UD.messages.append(1l);
        String message = mess.getContent().get();
        UD.letters.append(message.length());
    }
    
    public void send(String message, Snowflake ID){
        MessageChannel chan = (MessageChannel) client.getChannelById(ID).block();
        
        chan.createMessage(message).subscribe();
    }
    
    public void send(String message, User U){
        MessageChannel chan = U.getPrivateChannel().block();
        
        chan.createMessage(message).subscribe();
    }
    
    public void send(String message, UserData UD){
        User U = client.getUserById(UD.snow).block();
        
        send(message, U);
    }
    
}
