/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import bot.Launcher;
import data.UserData;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;
import java.util.List;
import model.Commune;

/**
 *
 * @author FF6EB
 */
public class CommunCheck extends Command{
    public CommunCheck(){
        hidden = false;
        ident = new String[]{
            "com",
            "commune",
            "community"
        };
        description = "Displays the commune's information!";
        super.addCommand();
    }
    public String execute(Message mess){
        String ret = "**COMMUNITY RESOURCES**\n";
        ret = ret+"```"+Commune.inventory+"```";
        
        ret+= "\n**JOBS BEING DONE**\n";
        for(UserData ud : Launcher.jobs.getUsers()){
            ret+= ud.SKName.toString()+": "+ud.currentJob.toString()+"\n";
        }
        
        return ret;
    }
}
