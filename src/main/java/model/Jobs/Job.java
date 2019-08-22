/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Jobs;

import data.UserData;
import java.util.ArrayList;
import model.Commune;
import model.Levels;
import static model.Levels.EMPATHY;
import static model.Levels.ENDURANCE;
import static model.Levels.HUNGER;
import static model.Levels.LIBIDO;
import static model.Levels.LIFE_FORCE;
import static model.Levels.LUCK;
import static model.Levels.PHYSICAL;

/**
 *
 * @author FF6EB
 */
public class Job {
    private String title;
    private String description;
    
    private String product;
    private String material;
    private long salary;
    
    private int skill;
    private long level;
    private long time; //Time in millis to produce one unit
    
    Job(){}
    
    public String getStats(){
        String ret = "Tick Time: "+(time/1000)+" seconds";
        ret+="\nProduct: "+product;
        ret+="\nIntake: "+material;
        ret+="\nSalary: "+salary;
        
        return ret;
    }
    
    public long getTickTime(){
        return time;
    }
    
    public void setBase(long time, long level, int skill){
        this.time = time;
        this.level = level;
        this.skill = skill;
    }
    
    public void setFlavor(String title, String desc){
        this.title = title;
        this.description = desc;
    }
    
    public void setProduction(String product, String material, long sal){
        this.product = product;
        this.material = material;
        this.salary = sal;
    }
    
    private static ArrayList<Job> jobList = loadJobList();
    private static ArrayList<Job> loadJobList(){
        ArrayList<Job> ret = new ArrayList<>();
        
        BasicJob j = new BasicJob(1, PHYSICAL);        
        j.setFlavor("Shoveling Shit", "Produces 💩 Shit");
        j.setProduction("💩 Shit","None",1);
        ret.add(j);
        
        j = new BasicJob(2, PHYSICAL);        
        j.setFlavor("Hauling Ass", "Blood, sweat and tears.");
        j.setProduction("🍑 Peaches","😭 Tears",2);
        ret.add(j);
        
        j = new BasicJob(5, PHYSICAL);        
        j.setFlavor("Beat Up Haters", "They had it coming");
        j.setProduction("🥫 Canned Beets","⬇ Raw Hatred For Mankind",7);
        ret.add(j);
        
        j = new BasicJob(1,HUNGER);
        j.setFlavor("Tossing Shit At Children", "Consumes 💩 Shit to produce 😭 Tears!");
        j.setProduction("😭 Tears","💩 Shit",2);
        ret.add(j);
        
        j = new BasicJob(4,HUNGER);
        j.setFlavor("Repost On Reddit", "YOU MONSTER");
        j.setProduction("⬇ Raw Hatred For Mankind","🌿 Memes",7);
        ret.add(j);
        
        j = new BasicJob(8,HUNGER);
        j.setFlavor("Cry", "Wait you didn't need to throw anything...");
        j.setProduction("😭 Tears","None",2);
        ret.add(j);
        
        j = new BasicJob(1,LIBIDO);
        j.setFlavor("Fermenting Tears", "Consumes 😭 Tears to produce 🍷 Fine Whine!");
        j.setProduction("🍷 Fine Whine","😭 Tears",3);
        ret.add(j);
        
        j = new BasicJob(4,LIBIDO);
        j.setFlavor("Eating Ass", "🍑🍑🍑🍑🍑🍑");
        j.setProduction("🍌 Banana","🍑 Peaches",3);
        ret.add(j);
        
        j = new BasicJob(6,LIBIDO);
        j.setFlavor("Eating Bananas", "You know exactly what this produces");
        j.setProduction("❤ The Gay","🍌 Banana",3);
        ret.add(j);
        
        j = new BasicJob(1,EMPATHY);
        j.setFlavor("Feeding The Pigs Whine", "Consumes 🍷 Fine Whine to produce 🥓 Bacon!");
        j.setProduction("🥓 Bacon","🍷 Fine Whine",4);
        ret.add(j);
        
        j = new BasicJob(3,EMPATHY);
        j.setFlavor("Calming Shit Covered Children", "You hate to do it.");
        j.setProduction("❤ The Gay","😭 Tears",4);
        ret.add(j);
        
        j = new BasicJob(7,EMPATHY);
        j.setFlavor("Solving Puzzles", "It's a puzzle!");
        j.setProduction("😕 Confusion","None",3);
        ret.add(j);
        
        j = new BasicJob(1,LIFE_FORCE);
        j.setFlavor("Consuming Bacon", "Consumes 🥓 Bacon to produce 💩 Shit!");
        j.setProduction("💩 Shit","🥓 Bacon",5);
        ret.add(j);
        
        j = new BasicJob(3,LIFE_FORCE);
        j.setFlavor("Dank Kush", "Siiiick");
        j.setProduction("🌿 Memes","💀 Illness",6);
        ret.add(j);
        
        j = new BasicJob(9,LIFE_FORCE);
        j.setFlavor("Producing Gay Porn", "⚣⚣⚣⚣");
        j.setProduction("💦 Sweat Droplets","❤ The Gay",7);
        ret.add(j);
        
        j = new BasicJob(1,ENDURANCE);
        j.setFlavor("Eating Shit And Dying", "Consumes 💩 Shit to produce 💀 Illness!");
        j.setProduction("💀 Illness","💩 Shit",2);
        ret.add(j);
        
        j = new BasicJob(4,ENDURANCE);
        j.setFlavor("Chopping Banana", "Oh god it hurts");
        j.setProduction("🥩 Cut Meat","🍌 Banana",3);
        ret.add(j);
        
        j = new BasicJob(10,ENDURANCE);
        j.setFlavor("Practice Religion", "If this doesn't offend someone I'm going home");
        j.setProduction("🛐 Child Molestation","❤ The Gay",10);
        ret.add(j);
        
        j = new BasicJob(1,LUCK);
        j.setFlavor("Search Through The Shit", "Consumes 💩 Shit to produce 🍀 Shit Luck!");
        j.setProduction("🍀 Shit Luck","💩 Shit",2);
        ret.add(j);
        
        j = new BasicJob(4,LUCK);
        j.setFlavor("Catching Monkeys", "Never ask a LADY about her MONKEY");
        j.setProduction("🐒 Monkey","🥩 Cut Meat",5);
        ret.add(j);
        
        j = new BasicJob(9,LUCK);
        j.setFlavor("Surprise Mechanics", "It doesn't have to make sense!");
        j.setProduction("😕 Confusion","🥓 Bacon",5);
        ret.add(j);
        
        return ret;
    }
    
    public static String applicableJobs(UserData UD){
        String ret = "Jobs you can work: \n";
        for(Job J : jobList){
            if(J.applicable(UD)){
                ret+="> "+J.toString();
            }
        }
        ret+="\n*Type job <jobtitle> to work that job!*";
        return ret;
    }
    
    public String toString(){
        return title+" *("+description+")*\n";
    }
    
    public static boolean isJob(String jobName){
        for(Job J : jobList){
            if(J.title.toLowerCase().trim().equals(jobName.toLowerCase().trim())){
                return true;
            }
        }
        return false;
    }
    
    public static Job getJob(String jobName){
        for(Job J : jobList){
            if(J.title.toLowerCase().trim().equals(jobName.toLowerCase().trim())){
                return J;
            }
        }
        return null;
    }
    
    public boolean applicable(UserData UD){
        String ski = Levels.getStatName(skill);
        
        if(!this.material.equals("None")){
            if(!Commune.inventory.has(this.material)){
                return false;
            }
        }
        
        return UD.levels.hasLevel(ski, level);
    }
    
    public void go(UserData UD){
        if(!this.material.equals("None")){
            if(Commune.inventory.has(material, 1)){
                Commune.inventory.take(material, 1);
            } else {
                return;
            }
        }
        
        
        Commune.inventory.give(this.product);
        UD.dressing.append(salary);
        UD.levels.give(Levels.getStatName(this.skill),1);
    }
}
