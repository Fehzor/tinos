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
public class InvCom extends Command{
    public InvCom(){
        hidden = false;
        ident = new String[]{
            "inventory",
            "inv",
            "stuff",
            "stats",
            "me"
        };
        description = "Displays the user's inventory!";
        super.addCommand();
    }
    public String execute(Message mess){
        User u = mess.getAuthor().get();
        UserData UD = UserData.getUD(u);
        String ret = UD.inv();
        return ret;
    }
}
