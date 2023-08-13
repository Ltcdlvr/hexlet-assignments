package exercise;

import java.util.*;

// BEGIN
public class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> firstKeys = new HashSet<>(firstMap.keySet());
        Set<String> secondKeys = new HashSet<>(secondMap.keySet());

        System.out.println("FK" + firstKeys);
        System.out.println("SK" + secondKeys);

        Set<String> intersectionKeys = new HashSet<>(firstKeys);
        intersectionKeys.retainAll(secondKeys);
        System.out.println("Intersection" + intersectionKeys);

        // result
        SortedMap<String, String> sortedResult = new TreeMap<>();

        // deleted keys
        firstKeys.removeAll(intersectionKeys);
        for (String deletedKey: firstKeys) {
            sortedResult.put(deletedKey, "deleted");
        }

        // added keys
        secondKeys.removeAll(intersectionKeys);
        for (String addedKey: secondKeys) {
            sortedResult.put(addedKey, "added");
        }

        for (String key: intersectionKeys) {
            Object firstObj = firstMap.get(key);
            Object secondObj = secondMap.get(key);

            if (firstObj.equals(secondObj)) {
                sortedResult.put(key, "unchanged");
            } else {
                sortedResult.put(key, "changed");
            }
        }

        return new LinkedHashMap<>(sortedResult);
    }
}
//END
