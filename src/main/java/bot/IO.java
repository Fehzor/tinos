/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import static bot.Launcher.client;
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

/**
 *
 * @author FF6EB
 */
public class IO extends Thread{
    DiscordClient client;
    
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
        System.out.println("INITIALIZING BOT");
    }
    
    private void listen(Message mess){
        if(mess.getAuthor().get().isBot()){
            //If it's a bot, we don't listen to what it tells us.
            return;
        }
        String channelName = ((TextChannel)mess.getChannel().block()).getName();
        String userName = mess.getAuthor().get().getUsername();
        
        System.out.println(channelName+"/"+userName+"> "+mess.getContent().get());
    }
    
    public void send(String message, Snowflake ID){
        MessageChannel chan = (MessageChannel) client.getChannelById(ID).block();
        
        chan.createMessage(message).subscribe();
    }
}
