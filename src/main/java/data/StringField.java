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
public class StringField extends Field{
    
    public StringField(String s, Object... keys){
        super(s, keys);
    }
    
    public StringField(Object... keys){
        super("", keys);
    }
    
    public String append(String s){
        data = (String)data + s;
        this.writeData(data);
        return (String)data;
    }
}
