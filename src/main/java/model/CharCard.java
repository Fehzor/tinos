/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author FF6EB
 */
public class CharCard extends Card implements Serializable{
    private CharCard(char symbol, String name, String url, long owner){
        super(symbol, name, url, owner);
    }
    
    private char start = '0';
    private String sequence = "";
    
    public boolean createCard(char sym, String name, String url, long owner){
        Card get = allCards.get(makeToken());
        if(get == null){
            new CharCard(sym,name,url,owner);
            return true;
        } else {
            return false;
        }
    }
    
    public void set(char start, String sequence){
        this.start = start;
        this.sequence = sequence;
    }
}
