/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import bot.main.Settings;
import data.UserData;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;

/**
 *
 * @author FF6EB
 */
public class SetGuildCom extends Command{
    public SetGuildCom(){
        hidden = false;
        ident = new String[]{
            "setguild"
        };
        description = "Sets your guild!";
        super.addCommand();
    }
    public String execute(Message mess){
        
        String [] split = mess.getContent().get().split(" ",2);
        if(split.length > 1){
            String name = split[1];
            
            UserData UD = UserData.getUD(mess.getAuthor().get());
            UD.SKGuild.set(name);
            
            if(name.toLowerCase().equals("tinos ranch")){
                User U = mess.getAuthor().get();
                Member M = mess.getGuild().block().getMemberById(U.getId()).block();
                
                Snowflake NOTroleID = Snowflake.of((long)Settings.NOTbabyDucksRoleID);
                Snowflake PLUSroleID = Snowflake.of((long)Settings.babyDucksRoleID);
                
                M.removeRole(NOTroleID,"Not a member of Tinos Ranch").block();
                M.addRole(PLUSroleID,"Member of Tinos Ranch").block();
            } else {
                User U = mess.getAuthor().get();
                Member M = mess.getGuild().block().getMemberById(U.getId()).block();
                
                Snowflake NOTroleID = Snowflake.of((long)Settings.babyDucksRoleID);
                Snowflake PLUSroleID = Snowflake.of((long)Settings.NOTbabyDucksRoleID);
                
                M.removeRole(NOTroleID,"Not not a member of Tinos Ranch").block();
                M.addRole(PLUSroleID,"Not a member of Tinos Ranch").block();
            }
            
            return "Guild set to "+name+"!";
        } else {
        
            return "Enter your guild name after the command-eg setguild Tinos Ranch";
        
        }
    }
}
