/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.MapField;
import discord4j.core.object.entity.User;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author FF6EB
 */
public class Coin implements Serializable{
    String name;
    char symbol;
    long owner;
    
    public String toString(){
        return symbol+" "+name;
    }
    
    private static MapField<Coin> bySymbol = new MapField<Coin>("coins","bySymbol");
    private static MapField<Coin> byName = new MapField<Coin>("coins","byName");
    private static MapField<Coin> byOwner = new MapField<Coin>("coins","byOwner");
    
    private Coin(char sym, String name, long id){
        this.name = name;
        this.symbol = sym;
        this.owner = id;
        
        bySymbol.put(sym+"", this);
        byName.put(name, this);
        byOwner.put(id+"", this);
    }
    
    public static boolean mint(char symbol, String name, User creator){
        Coin getS = bySymbol.get(symbol+"");
        Coin getN = byName.get(name);
        Coin getO = byOwner.get(creator.getId().asLong()+"");
        
        if(getS == null && getN == null && getO == null){
            new Coin(symbol, name, creator.getId().asLong());
            return true;
        } else {
            return false;
        }
    }
    
    public static Coin get(char symbol){
        return bySymbol.get(symbol+"");
    }
    
    public static Coin get(String name){
        return byName.get(name);
    }
    
    public static Coin get(Long ID){
        return byOwner.get(ID+"");
    }
}
