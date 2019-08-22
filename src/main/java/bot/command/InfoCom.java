/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import data.UserData;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;
import java.util.List;

/**
 *
 * @author FF6EB
 */
public class InfoCom extends Command{
    public InfoCom(){
        hidden = false;
        ident = new String[]{
            "info"
        };
        description = "Displays the user's information!";
        super.addCommand();
    }
    public String execute(Message mess){
        
        String [] split = mess.getContent().get().split(" ",2);
        if(split.length > 1){
            String name = split[1];
            name = name.toLowerCase();
            if(mess.getUserMentions().hasElements().block()){
                User get = mess.getUserMentions().blockFirst();

                UserData UD = UserData.getUD(get);
                String ret = UD.inv();
                return ret;
            } else {
                try{
                    List<Member> members = mess.getGuild().block().getMembers().collectList().block();
                    for(Member M : members){
                        String username = M.getUsername();
                        String nickname = M.getDisplayName();
                        if(username.toLowerCase().equals(name) || nickname.toLowerCase().equals(name)){
                            UserData UD = UserData.getUD((User)M);

                            String ret = UD.inv();
                            
                            return ret;
                        }
                    }
                    
                    for(Member M : members){
                        String username = M.getUsername();
                        String nickname = M.getDisplayName();
                        if(username.toLowerCase().contains(name) || nickname.toLowerCase().contains(name)){
                            UserData UD = UserData.getUD((User)M);

                            String ret = UD.inv();
                            
                            return ret;
                        }
                    }
                } catch (Exception E2){
                    E2.printStackTrace();
                }
            }
        } else {
        
            User u = mess.getAuthor().get();
            UserData UD = UserData.getUD(u);
            String ret = UD.inv();
            return ret;
        
        }
        
        return "User Not Found";
    }
}
