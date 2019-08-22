/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import bot.Launcher;
import data.UserData;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import model.Jobs.Job;

/**
 *
 * @author FF6EB
 */
public class SetJob extends Command{
    public SetJob(){
        hidden = false;
        ident = new String[]{
            "job"
        };
        description = "Sets your job or views jobs!";
        super.addCommand();
    }
    public String execute(Message mess){
        UserData UD = UserData.getUD(mess.getAuthor().get());
        
        String [] split = mess.getContent().get().split(" ",2);
        if(split.length > 1){
            String name = split[1];
            
            if(Job.isJob(name)){
                UD.currentJob.set(name);
                UD.startJob.writeData(System.currentTimeMillis());
                Launcher.jobs.addUser(mess.getAuthor().get());
                return "Now working job: "+name+"!";
            } else {
                return "Wait! "+name+" isn't a job! Check spelling?";
            }
            
            
        } else {
            String jobs = Job.applicableJobs(UD);
            
            return jobs;
        }
    }
}
