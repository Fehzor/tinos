/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.channels;

import bot.main.IO;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;

/**
 *
 * @author FF6EB
 */
public class MainChannel {
    Snowflake flake;
    IO io;
    
    public MainChannel(IO io, long ID){
        this.flake = Snowflake.of(ID);
        this.io = io;
    }
    
    public void send(String message){
        io.send(message, flake);
    }
    
    public void receive(User u, String message){
        
    }
}
