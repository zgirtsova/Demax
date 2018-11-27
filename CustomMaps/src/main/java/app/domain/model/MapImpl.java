package app.domain.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class MapImpl<K, V> implements MyMap<K, V> {

    @Expose
    private K key;

    @Expose
    private V value;

    private Map<K, V> myMap;



    public MapImpl() {
        this.myMap = new HashMap<>();
    }


    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Map<K, V> getMyMap() {
        return myMap;
    }

    public void setMyMap(Map<K, V> myMap) {
        this.myMap = myMap;
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