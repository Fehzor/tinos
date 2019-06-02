/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import discord4j.core.object.entity.Message;
import java.util.HashMap;

/**
 *
 * @author FF6EB
 */
public class Command {
    
    private static HashMap<String, Command> commands = new HashMap<>();
    
    String [] ident;
    String description;
    boolean hidden;
    
    public Command(){
        ident = new String[]{};
        description = "";
        hidden = true;
    }
    
    void addCommand(){
        for(String id : ident){
            commands.put(id, this);
        }
    }
    
    public static Command get(String s){
        return commands.get(s);
    }
    
    public String execute(Message arg){
        return "Default command has been called!";
    }
}
