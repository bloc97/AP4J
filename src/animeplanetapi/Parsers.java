/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animeplanetapi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bowen
 */
public abstract class Parsers {
    
    
    public static Map<String, String> parseType(String typeString) {
        String type = "";
        String episodes = "N/A";
        String minutesPerEpisode = "N/A";
        
        int sI = typeString.indexOf('(');
        int eI = typeString.lastIndexOf(')');
        if (sI != -1 && eI != -1 && eI - sI > 1) { //if can find episodes count
            String episodesString = typeString.substring(sI + 1, eI);
            typeString = typeString.substring(0, sI).trim();
            int epI = episodesString.indexOf("ep");
            int xI = episodesString.indexOf('x');
            int minI = episodesString.lastIndexOf("min");

            if (epI > -1 && xI > -1 && xI > epI && minI > -1 && minI - xI > 1) { //if can find minutes count
                String minutesString = episodesString.substring(xI + 1, minI).trim();
                episodesString = episodesString.substring(0, epI).trim();
                type = typeString;
                episodes = episodesString;
                minutesPerEpisode = minutesString;
            } else if (epI > -1) {
                episodesString = episodesString.substring(0, epI).trim();
                type = typeString;
                episodes = episodesString;
            } else {
                type = typeString;
            }

        } else {
            type = typeString;
        }
        
        Map<String, String> map = new HashMap();
        map.put("type", type);
        map.put("episodes", episodes);
        map.put("minutesPerEpisode", minutesPerEpisode);
        
        return map;
    }
    
    public static Map<String, String> parseYear(String yearString) {
        
        String beginYear, endYear;
        
        int yI = yearString.indexOf('-');
        if (yI > -1) {
            beginYear = yearString.substring(0, yI).trim();
            endYear = yearString.substring(yI + 1).trim();
        } else {
            beginYear = yearString;
            endYear = yearString;
        }
        
        Map<String, String> map = new HashMap();
        map.put("beginYear", beginYear);
        map.put("endYear", endYear);
        
        return map;
    }
    
    public static String formatIllegalSpace(String string) {
        return string.replace(" ", "%20");
    }
    
}
