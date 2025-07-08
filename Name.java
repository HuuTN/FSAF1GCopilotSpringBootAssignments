import java.util.Collections;
import java.util.List;

public class Name {
    public List<String> sortNames(List<String> names) {
        Collections.sort(names);
        return names;
    }

    //Optimize the sortNames method to use Java Streams for case-insensitive sorting
    // not using String.CASE_INSENSITIVE_ORDER , use collect() instead of toList()
    public List<String> sortNamesCaseInsensitive(List<String> names) {
        return names.stream()
                .sorted(String::compareToIgnoreCase)
                .collect(java.util.stream.Collectors.toList());
    }
}
