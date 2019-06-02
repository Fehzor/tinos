/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import bot.main.IO;
import discord4j.core.object.entity.Message;

/**
 *
 * @author FF6EB
 */
public class CommandParser {
    public void parseCommand(IO io, Message mess){
        
        String S = mess.getContent().get();
        
        String[] split = S.split(" ",2);
        String signature = split[0];
        signature = signature.toLowerCase();
        
        try{
            String result = Command.get(signature).execute(mess);
            io.send(result, mess.getChannelId());
        } catch (Exception E){
            System.err.println("ERROR IN COMMANDPARSER- "+mess.getContent().get());
        }
    }
    
    private static CommandParser CP = new CommandParser();
    private CommandParser(){
        //Instantiate commands here :D
    }
}
