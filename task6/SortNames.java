import java.util.Collections;
import java.util.List;

public class SortNames {
    public List<String> sortNames(List<String> names) {
        Collections.sort(names);
        return names;
    }

    // Optimize the sortNames method to use Java Streams for case-insensitive sorting
    public List<String> sortNamesCaseInsensitive(List<String> names) {
        return names.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
    }
}
