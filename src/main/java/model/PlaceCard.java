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
public class PlaceCard extends Card implements Serializable{
    private PlaceCard(char symbol, String name, String url, long owner){
        super(symbol, name, url, owner);
    }
    
    long[][]boardA = new long[3][3];
    long[][]boardB = new long[3][3];
    
    public boolean createCard(char sym, String name, String url, long owner){
        Card get = allCards.get(makeToken());
        if(get == null){
            new PlaceCard(sym,name,url,owner);
            return true;
        } else {
            return false;
        }
    }
    
    public void setA(long[][] board){
        this.boardA = board;
    }
    
    public void setB(long[][] board){
        this.boardB = board;
    }
    
    public long[][] getBoardA(){
        return boardA;
    }
    
    public long[][] getBoardB(){
        return boardB;
    }
}
