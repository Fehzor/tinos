/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bot.main.IO;
import data.MapField;
import data.UserData;
import discord4j.core.object.entity.User;
import model.Square;

/**
 *
 * @author FF6EB
 */
public class PickupEvent extends TimeEvent{
    private static final long TIME = 1000 * 60 * 3; //1 tick = 3 minute for now
    
    private MapField<Long> tickList;
    public PickupEvent(IO io){
        super(io, TIME);
        tickList = new MapField<Long>("pickup","ticks");
    }
    
    public void addUser(User U){
        super.addUser(U);
        tickList.put(U.getId().asLong()+"", 0l);
    }
    
    public void execute(UserData UD){
        long tick = tickList.get(UD.snow.asLong()+"");
        tickList.append(UD.snow.asLong()+"", 1l);
        
        if(tick == 0l){
            UD.squares.give(Square.getRandom().toString(),3);
            UD.squares.give(Square.getRandom().toString(),3);
            UD.squares.give(Square.getRandom().toString(),3);
            
            io.send("Found squares! You now have-\n"+UD.squares, UD);
        }
        
        if(tick == 1l){
            UD.squares.give(Square.getRandom().toString(),3);
            UD.squares.give(Square.getRandom().toString(),3);
            io.send("Found squares! You now have- \n"+UD.squares, UD);
        }
        
        if(tick == 3l){
            UD.squares.give(Square.getRandom().toString(),3);
            io.send("Found squares! You now have- \n"+UD.squares, UD);
        }
    }
}
