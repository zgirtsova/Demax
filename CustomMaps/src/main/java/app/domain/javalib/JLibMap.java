package app.domain.javalib;

import java.io.Serializable;
import java.util.Map;

public interface JLibMap<K, V> extends Serializable {
    
    void put(K key, V value);

    Object get(K key);
    
    boolean contains(K key);

    boolean remove(K key);

    Map<K, V> getInnerMap();

    void saveMap();

}