/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import data.Field;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import discord4j.core.object.util.Snowflake;
import model.Model;

/**
 *
 * @author FF6EB
 */
public class Launcher {
    
    public static DiscordClient client;
    
    public static void main(String [] args){
        if(args.length != 1){
            return;
        }
        
        IO io = new IO(args[0]);
        
        Model M = new Model(io);
    }
}
