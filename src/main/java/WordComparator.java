import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by heymn on 22.01.2019.
 */
public class WordComparator implements Comparator<Map.Entry<String, Integer>>{
    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b)
    {
        if (a.getValue() < b.getValue()) return 1;
        if (a.getValue() > b.getValue()) {
            return -1;
        }
        return a.getKey().compareTo(b.getKey());

    }



}
