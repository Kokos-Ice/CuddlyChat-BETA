package tools;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
 

public class SortedMapUtils
{
   
    public static <K, V extends Comparable<? super V>> Map<K, V> 
        createMapSortedByValues(Map<K, V> map)
    {
        return createMapSortedByValuesAndKeys(map, 
            SortedMapUtils.<V>comparableComparator(),
            SortedMapUtils.<K>fallbackComparator());
    }
    
    
    public static <
        K extends Comparable<? super K>, 
        V extends Comparable<? super V>> Map<K, V> 
        createMapSortedByValuesAndKeys(Map<K, V> map)
    {
        return createMapSortedByValuesAndKeys(map,
            SortedMapUtils.<V>comparableComparator(),
            SortedMapUtils.<K>comparableComparator());
    }
    
    
   
    public static <K, V> Map<K, V> createMapSortedByValuesAndKeys(
        Map<K, V> map, 
        final Comparator<V> valueComparator,
        final Comparator<K> keyComparator)
    {
        final Map<K, V> backingMap = new HashMap<K, V>(map);
        Map<K, V> result = new TreeMap<K, V>(new Comparator<K>()
        {
            @Override
            public int compare(K k0, K k1)
            {
                V v0 = backingMap.get(k0);
                V v1 = backingMap.get(k1);
                int c = valueComparator.compare(v0, v1);
                if (c != 0)
                {
                    return c;
                }
                return keyComparator.compare(k0, k1);
            }
        });
        result.putAll(map);
        return result;
    }
 
   
    public static <T extends Comparable<? super T>> Comparator<T> 
        comparableComparator()
    {
        return new Comparator<T>()
        {
            @Override
            public int compare(T t0, T t1)
            {
                return t0.compareTo(t1);
            }
        };
    }
    
   
    
    public static <T> Comparator<T> fallbackComparator()
    {
        return new Comparator<T>()
        {
            @Override
            public int compare(T t0, T t1)
            {
                int h0 = System.identityHashCode(t0);
                int h1 = System.identityHashCode(t1);
                if (h0 > h1)
                {
                    return -1;
                }
                if (h0 < h1)
                {
                    return 1;
                }
                return 0;
            }
            
        };
    }
    
   
    private SortedMapUtils()
    {
    }
}