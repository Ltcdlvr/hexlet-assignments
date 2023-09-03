package exercise;

import java.util.*;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        apartments.sort(new Comparator<Home>() {
            @Override
            public int compare(Home o1, Home o2) {
                return o1.compareTo(o2);
            }
        });

        List<String> result = new ArrayList<>();
        if (n > apartments.size()) {
            return result;
        }

        for (Home home: apartments.subList(0, n)) {
            result.add(home.toString());
        }
        return result;
    }
}
// END
