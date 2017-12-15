

package animeplanetapi;

public class Levenshtein {

    public static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
    
    public static int substringDistance(String longString, String shortString) { //Use this to weight in favour of a substring
        //Eg "Auto" will be closer to "Automobile" than "Audi" if used with Math.min(Levenstehin, substringDistance)
        if (longString.startsWith(shortString)) {
            final int longLength = longString.length();
            final int shortLength = shortString.length();
            return (int)Math.log10(longLength - shortLength);
            //return (int)Math.pow(longLength - shortLength, 1/4f);
            //return (int)Math.sqrt(longLength - shortLength);
            //return 0;
            //return (longString.length() - shortString.length())/4;
        }
        return distance(longString, shortString);
        //return Integer.MAX_VALUE;
    }
    public static int subwordDistance(String longString, String shortString) { //Use this to weight in favour of a subword or substring
        //Eg "Auto" will be closer to "Cold Auto" than "Audi"
        if (longString.startsWith(shortString) || longString.contains(" " + shortString)) {
            final int longLength = longString.length();
            final int shortLength = shortString.length();
            return Math.min((int)Math.log10(longLength - shortLength), distance(longString, shortString));
        }
        return distance(longString, shortString);
    }
    
    public static String toLowerCase(String s) {
        if (s == null) {
            return "                                         ";
        } else {
            return s.toLowerCase();
        }
    }
}