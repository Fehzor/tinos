/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author FF6EB
 */
public class Square{
    private static HashMap<String, Square> squares = new HashMap<>();
    
    private Square(long A, long B){
        this.A = A;
        this.B = B;
        
        squares.put(this+"",this);
    }
    
    public static Square getSquare(long A, long B){
        String get = toString(A,B);
        if(squares.containsKey(get)){
            return squares.get(get);
        } else {
            return new Square(A, B);
        }
    }
    
    private long A;
    private long B;
    

    public void set(long A, long B){
        this.A = A;
        this.B = B;
    }
    
    public long A(){
        return A;
    }
    
    public long B(){
        return B;
    }
    
    public String toString(){
        return "A: "+A+"\nB: "+B;
    }
    
    public static String toString(long A, long B){
        return "["+A+","+B+"]";
    }
}
