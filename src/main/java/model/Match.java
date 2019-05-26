/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static bot.SuperRandom.oRan;

/**
 *
 * @author FF6EB
 */
public class Match {
    private PlaceCard place;
    private CharCard A;
    private CharCard B;
    
    private int aRow;
    private int bCol;
    
    private long aRes;
    private long bRes;
    
    
    public Match(){}
    
    public void set(PlaceCard where, CharCard A, CharCard B){
        this.place = where;
        this.A = A;
        this.B = B;
        
        aRow = start(true, A.start());
        bCol = start(false, B.start());
        
        sequence(A.sequence(),B.sequence());
    }
    
    public long resultA(){
        return aRes;
    }
    
    public long resultB(){
        return bRes;
    }
    
    public int start(boolean row, char code){
        switch(code){
            case '0':
                return 0;
            case 'R':
                if(row){
                    return oRan.nextInt(place.getBoardA().length);
                } else {
                    return oRan.nextInt(place.getBoardB()[0].length);
                }
            case 'E':
                if(row){
                    return place.getBoardA().length;
                } else {
                    return place.getBoardB()[0].length;
                }
            default:
                return 0;
        }
    }
    
    public void sequence(String aSeq, String bSeq){
        int index = 0;
        while(index < aSeq.length() && index < bSeq.length()){
            
            int nextRow = turn(true, aSeq.charAt(index));
            int nextCol = turn(false, bSeq.charAt(index));
            
            aRow = nextRow;
            bCol = nextCol;
            
            ++index;
        }
        
        while(index < aSeq.length()){
            aRow = turn(true, aSeq.charAt(index));
            index++;
        }
        while(index < bSeq.length()){
            bCol = turn(false, bSeq.charAt(index));
            index++;
        }
        
        long[][]boardA = place.getBoardA();
        long[][]boardB = place.getBoardB();
        
        aRes = boardA[aRow][bCol];
        bRes = boardB[aRow][bCol];
    }
    
    public int turn(boolean row, char c){
        switch(c){
            case '+':
                return plus(row);
            case '0':
                return 0;
            case 'R':
                if(row){
                    return oRan.nextInt(place.getBoardA().length);
                } else {
                    return oRan.nextInt(place.getBoardB()[0].length);
                }
            case 'E':
                if(row){
                    return place.getBoardA().length;
                } else {
                    return place.getBoardB()[0].length;
                }
        }
        
        return -1;
    }
    
    
    
    public int plus(boolean row){
        int ret = -1;
        
        if(row){
            long[][]board = place.getBoardA();
            
            long best = board[aRow][bCol];
            ret = aRow;
            
            for(int i = 0; i < board.length; ++i){
                if(board[i][bCol] > best){
                    best = board[i][bCol];
                    ret = i;
                }
            }
            
        } else {
            long[][]board = place.getBoardB();
            
            long best = board[aRow][bCol];
            ret = bCol;
            
            for(int i = 0; i < board[0].length; ++i){
                if(board[aRow][i] > best){
                    best = board[aRow][i];
                    ret = i;
                }
            }
            
        }
        
        return ret;
    }
}
