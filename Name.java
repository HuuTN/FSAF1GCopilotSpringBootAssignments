import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Name {
    public List<String> sortNames(List<String> names) {
        Collections.sort(names);
        return names;
    }

    //Optimized the sortNams method to use JavaStreams for case-insensitive sorting with not using String.CASE_INSENSITIVE_ORDER , use method collect()
    public List<String> sortNamesCaseInsensitive(List<String> names) {
        return names.stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
    }
    
    
}
