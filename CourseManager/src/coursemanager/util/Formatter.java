/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursemanager.util;

/**
 *
 * @author NgocHien-PC
 */
public class Formatter {
    public static String normalizeId(String s){
        StringBuilder newStr = new StringBuilder();
        for( int i = 0; i < s.length(); i++){
            if(!Character.isSpaceChar(s.charAt(i))){
                newStr.append(s.charAt(i));
            }
        }
        return newStr.toString().toUpperCase();
    }
    
    public static String normalizeName(String s){
        String[] word = s.split("\\s+");
        StringBuilder newStr = new StringBuilder();
        for(String w : word){
            newStr.append((w.charAt(0)+"").toUpperCase());
            newStr.append(w.substring(1).toLowerCase() + " ");
        }
        return newStr.toString().trim();        
    }
}
