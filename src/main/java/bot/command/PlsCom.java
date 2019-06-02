/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.command;

import discord4j.core.object.entity.Message;

/**
 *
 * @author FF6EB
 */
public class PlsCom extends Command{
    public PlsCom(){
        hidden = true;
        ident = new String[]{
            "pls",
            "what"
        };
        description = "¯\\_(ツ)_/¯";
        super.addCommand();
    }
    public String execute(Message mess){
        return "¯\\_(ツ)_/¯";
    }
}
