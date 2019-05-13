/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import static bot.Launcher.client;
import data.UserData;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.EventDispatcher;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Channel;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import discord4j.core.object.entity.TextChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.object.util.Snowflake;
import reactor.core.publisher.Flux;
import model.Model;

/**
 *
 * @author FF6EB
 */
public class IO extends Thread{
    public DiscordClient client;
    
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
        
        client.login().block();
    }
    
    private void init(ReadyEvent ready){
        System.out.println("INITIALIZING IO");
    }
    
    private void listen(Message mess){
        if(mess.getAuthor().get().isBot()){
            //If it's a bot, we don't listen to what it tells us.
            return;
        }
        
        try{
            String channelName = ((TextChannel)mess.getChannel().block()).getName();
            String userName = mess.getAuthor().get().getUsername();

            System.out.println(channelName+"/"+userName+"> "+mess.getContent().get());

            if(mess.getContent().get().equals("!cast")){
                UserData UD = UserData.getUD(mess.getAuthor().get());
                if(!Model.fishers.contains(UD)){
                    Model.fishers.add(UD);
                }
            }
        } catch (Exception E){
            E.printStackTrace();
            System.err.println("BAD MESSAGE (Probably a picture rofl)");
        }
    }
    
    public void send(String message, Snowflake ID){
        MessageChannel chan = (MessageChannel) client.getChannelById(ID).block();
        
        chan.createMessage(message).subscribe();
    }
    
}
