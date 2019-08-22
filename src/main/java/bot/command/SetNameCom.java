/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import data.UserData;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;

/**
 *
 * @author FF6EB
 */
public class SetNameCom extends Command{
    public SetNameCom(){
        hidden = false;
        ident = new String[]{
            "setname"
        };
        description = "Sets your Spiral Knights identity!";
        super.addCommand();
    }
    public String execute(Message mess){
        
        String [] split = mess.getContent().get().split(" ",2);
        if(split.length > 1){
            String name = split[1];
            
            UserData UD = UserData.getUD(mess.getAuthor().get());
            UD.SKName.set(name);
            
            return "SK name set to "+name+"!";
        } else {
        
            return "Enter your name after the command-eg setname Fangels-Left-Nut";
        
        }
    }
}
