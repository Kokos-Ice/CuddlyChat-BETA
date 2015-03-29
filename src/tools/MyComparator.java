package tools;

import java.util.Comparator;

public class MyComparator implements Comparator {
    @Override
    public int compare(Object t, Object t1) {
        String o1 = String.valueOf(t);
        String o2 = String.valueOf(t1);
        if (o1.length() > o2.length()) {
            return -1;
        } else if (o1.length() < o2.length()) {
            return 1;
        } else {
            return 0;
        }
    }
}