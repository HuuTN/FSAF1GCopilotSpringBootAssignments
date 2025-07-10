import java.util.Collections;
import java.util.List;

public class Names {
    public List<String> sortNames(List<String> names) {
        Collections.sort(names);
        return names;
    }
    
    //Use String::compareToIgnoreCase instead of String.CASE_INSENSITIVE_ORDER
    //Use collect instead of toList
    // Optimize the sortNames method to use Java Streams for case-insensitive sorting
    public List<String> sortNamesCaseInsensitive(List<String> names) {
        return names.stream()
                    .sorted(String::compareToIgnoreCase)
                    .collect(Collectors.toList());
    }
}