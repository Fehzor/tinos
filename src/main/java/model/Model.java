/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.fish.Fish;

/**
 *
 * @author FF6EB
 */
public class Model {
    public ArrayList<Fish> stock;
    
    public void act(){
        for(Fish fish : stock){
            fish.swim();
        }
    }
}
