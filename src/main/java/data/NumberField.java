/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author FF6EB
 */
public class NumberField extends Field{
    
    public NumberField(long l, String... keys){
        super(l, keys);
        if(this.data == null){
            this.writeData(l);
        }
    }
    
    public NumberField(String... keys){
        super(keys);
        if(this.data == null){
            this.writeData(0l);
        }
    }
    
    public long append(long l){
        data = (long)data + (long)l;
        this.writeData(data);
        return (long)data;
    }
    
    public Long getData(){
        return ((Long)data).longValue();
    }
}
