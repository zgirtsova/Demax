package app.domain.model;

import com.google.gson.annotations.Expose;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MapImpl<K, V> implements MyMap<K, V> {

//    private K key;
//
//    private V value;

    private Map<K, V> myMap;



    public MapImpl(Map<K, V> outerMap) {
        this.myMap = outerMap;
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

    public void saveMap() {
        try
        {
            FileOutputStream fos =
                    new FileOutputStream("hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.myMap);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }
}