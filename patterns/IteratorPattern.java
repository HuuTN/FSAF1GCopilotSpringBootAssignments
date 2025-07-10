// Iterator pattern example
import java.util.*;
class NameRepository {
    private String[] names = {"John", "Jane", "Jack"};
    public Iterator<String> getIterator() {
        return Arrays.asList(names).iterator();
    }
}
