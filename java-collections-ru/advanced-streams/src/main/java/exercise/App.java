package exercise;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String getForwardedVariables(String configFile) {
        List<String> vars = List.of(configFile.split("\n"));
        String firstDelimiter = "environment";
        String secondDelimiter = "X_FORWARDED";

        List<String> envs = vars.stream()
                .filter(x -> x.startsWith(firstDelimiter))
                .map(x -> x.substring(firstDelimiter.length() + 2))
                .flatMap(x -> Stream.of(x.split(",")))
                .map(x -> x.replaceAll("\"",""))
                .filter(x -> x.startsWith(secondDelimiter))
                .map(x -> x.substring(secondDelimiter.length() + 1))
                .toList();

        StringJoiner sb = new StringJoiner(",");
        for(String env: envs) {
            sb.add(env);
        }

        return sb.toString();
    }
}
//END
