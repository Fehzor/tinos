/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FF6EB
 */
public class Field <Type>{
    String signature = "";
    Type data;
    
    private static HashMap<String,Field> fields = new HashMap<>();
    
    public Field(String... key){
        for(String k : key){
            signature += k+"_";
        }
        signature = signature.substring(0,signature.length()-1);
        
        if(fields.containsKey(key)){
            System.out.println("\nWARNING! FIELD ALREADY EXISTS IN DATA- "+signature+"\n");
        }
        
        Object get = load(signature);
        if(get != null){
            try{
                this.data = (Type)get;
            } catch (Exception E){
                System.out.println(signature+" was rotten! Creating as null...");
            }
        } else {
            this.data = null;
        }
        
        fields.put(signature,this);
        this.write();
    }
    
    public Field(Type init, String... key){
        for(String k : key){
            signature += k+"_";
        }
        signature = signature.substring(0,signature.length()-1);
        
        if(fields.containsKey(key)){
            System.out.println("\nWARNING! FIELD ALREADY EXISTS IN DATA- "+signature+"\n");
        }
        
        Object get = load(signature);
        if(get != null){
            try{
                this.data = (Type)get;
            } catch (Exception E){
                this.data = init;
                System.out.println(signature+" was rotten! Creating from init...");
            }
        } else {
            this.data = init;
        }
        
        fields.put(signature,this);
        this.write();
    }
    
    public Type getData(){
        return data;
    }
    
    public void writeData(Type data){
        this.data = data;
        this.write();
    }
    
    public Object invoke(String func, Object... parameters){
        try {
            Class[]parameterTypes = new Class[parameters.length];
            
            for(int i = 0; i < parameters.length; ++i){
                parameterTypes[i] = parameters[i].getClass();
            }
            
            Method m = data.getClass().getMethod(func, parameterTypes);
            
            Object ret = m.invoke(data, parameters);
            
            this.write();
            
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public String toString(){
        return data.toString();
    }
    
    private static Object load(String name){
        try{
            File F = new File("data/"+name);
            FileInputStream FIS = new FileInputStream(F);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            Object o = OIS.readObject();
            System.out.println("Loading..."+name);
            FIS.close();
            return o;
            
        } catch (Exception E){
            System.out.println("Failed to find "+name);
            return null;
        }
    }
    
    public void write(){
        try{
            File file = new File("data/"+signature);
            File parent = file.getParentFile();
            if (!parent.exists() && !parent.mkdirs()) { //create directory if it's missing
                System.out.println("Error: couldn't create data directory for file data/" + signature + "!");
                return;
            }
            FileOutputStream FOS = new FileOutputStream(file);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            
            OOS.writeObject(this.data);
            
            OOS.close();
        } catch (Exception E){
            System.out.println("Err saving field "+signature+"!  "+E);
        }
    }
    
    
}
