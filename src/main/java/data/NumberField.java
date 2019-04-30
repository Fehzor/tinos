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
    
    public NumberField(long l, Object... keys){
        super(l, keys);
    }
    
    public NumberField(Object... keys){
        super(0l, keys);
    }
    
    public long append(long l){
        data = (long)data + (long)l;
        this.writeData(data);
        return (long)data;
    }
}
