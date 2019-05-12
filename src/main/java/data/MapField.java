/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashMap;

/**
 *
 * @author FF6EB
 */
public class MapField<Type> extends Field{
    public MapField(Object... keys){
        super(keys);
        
        if(this.data == null){
            this.data = new HashMap<String, Type>();
            this.write();
        }
    }
    
    public Type get(String key){
        return ((HashMap<Long,Type>)data).get(key);
    }
    
    public void put(String key, Type val){
        ((HashMap<String,Type>)data).put(key, val);
        this.write();
    }
    
    public void append(String key, Type val){
        HashMap<String,Type> map = ((HashMap<String,Type>)data);
        
        if(map.containsKey(key)){
            if(map.get(key) instanceof Long){
                Long ret = (Long)val + (Long)map.get(key);
                map.put(key, (Type)ret);
            } else if(map.get(key) instanceof String){
                String ret = (String)val + (String)map.get(key);
                map.put(key, (Type)ret);
            } else {
                System.err.println("Operation not supported- only append using longs and strings!");
            }
        } else {
            map.put(key, val);
        }
        
        this.writeData(map);
    }
}
