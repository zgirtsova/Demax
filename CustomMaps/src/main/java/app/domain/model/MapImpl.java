package app.domain.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class MapImpl<K, V> implements MyMap<K, V> {

//    private K key;
//
//    private V value;

    private Map<K, V> myMap;



    public MapImpl() {
        this.myMap = new HashMap<>();
    }

    public Map<K, V> getMyMap() {
        return myMap;
    }

    @Override
    public void put(K key, V value) {
        this.myMap.put(key, value);

    }

    @Override
    public Object get(K key) {
        return this.myMap.get(key);
    }

    @Override
    public boolean contains(K key) {
        return this.myMap.containsKey(key);
    }

    @Override
    public boolean remove(K key) {
        if (this.myMap.containsKey(key)) {
            this.myMap.remove(key);
            return true;
        } else
            return false;
    }
}