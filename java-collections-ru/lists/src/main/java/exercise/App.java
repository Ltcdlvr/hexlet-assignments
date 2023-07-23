package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String strSymbols, String word) {
        List<String> symbols = new ArrayList<>(Arrays.asList(strSymbols.split("")));
        for (String letter: word.split("")) {
            if (!symbols.remove(letter.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
//END
