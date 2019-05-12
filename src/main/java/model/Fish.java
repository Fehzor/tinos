/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author FF6EB
 */
public class Fish {
    private static HashMap<String,Fish> fishMap = new HashMap<>();
    
    String name;
    
    public Fish(String name){
        fishMap.put(name, this);
    }
}
