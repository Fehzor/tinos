/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import discord4j.core.object.util.Snowflake;

/**
 *
 * @author FF6EB
 */
public class GameChannel {
    Snowflake flake;
    IO io;
    
    public GameChannel(IO io, long ID){
        this.flake = Snowflake.of(ID);
        this.io = io;
    }
    
    public void send(String message){
        io.send(message, flake);
    }
    
    
}
