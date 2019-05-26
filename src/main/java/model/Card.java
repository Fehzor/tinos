/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.MapField;
import java.io.Serializable;

/**
 *
 * @author FF6EB
 */
public abstract class Card implements Serializable{
    String name;
    char symbol;
    String URL;
    long owner;
    
    static MapField<Card> allCards = new MapField<Card>("cards");
    
    Card(char symbol, String name, String url, long owner){
        this.name = name;
        this.symbol = symbol;
        this.URL = url;
        this.owner = owner;
        
        allCards.put(makeToken(), this);
    }
    
    public String makeToken(){
        return symbol+"_"+name+"_"+owner;
    }
}
