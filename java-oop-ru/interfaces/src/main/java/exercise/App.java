package exercise;

import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int counts) {
        return apartments.stream()
                .sorted(Home::compareTo)
                .limit(counts)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
