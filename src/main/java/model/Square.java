/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static bot.SuperRandom.oRan;
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
        
        squares.put(this.toString(),this);
    }
    
    public static Square getSquare(long A, long B){
        String get = toString(A,B);
        if(squares.containsKey(get)){
            return squares.get(get);
        } else {
            return new Square(A, B);
        }
    }
    
    public static Square getRandom(){
        long a = oRan.nextInt(5) - 2;
        long b = oRan.nextInt(5) - 2;
        
        return getSquare(a,b);
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
        return "["+A+","+B+"]";
    }
    
    public static String toString(long A, long B){
        return "["+A+","+B+"]";
    }
}
