/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.main;

import data.MapField;

/**
 *
 * @author FF6EB
 */
public class Settings {
    public static MapField<Long> channels = new MapField<Long>("SETTINGS","CHANNELS");
    
    public static long babyDucksRoleID = Long.parseLong(System.getenv("DUCK"));
    public static long NOTbabyDucksRoleID = Long.parseLong(System.getenv("CHICKEN"));
}
