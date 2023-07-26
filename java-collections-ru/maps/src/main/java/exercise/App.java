package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String input) {
        Map <String, Integer> res = new HashMap<>();

        if (input.length() < 1) {
            return res;
        }

        for (String word: input.split(" ")) {
//            System.out.println(word);
            int oldVal = res.getOrDefault(word, 0);
            res.put(word, oldVal + 1);
        }
        return res;
    }

    public static String toString(Map<String, Integer> countedWords) {
        if (countedWords.size() < 1) {
            return "{}";
        }
        String res = "{\n";
        for (Map.Entry<String, Integer> wordResult: countedWords.entrySet()) {
            res += "  " + wordResult.getKey() + ": " + wordResult.getValue() + "\n";
        }
        res += "}";
        return res;
    }
}
//END
