package app.domain.javalib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class JLibImpl<K, V> implements JLibMap<K, V> {


    private Map<K, V> innerMap;



    public JLibImpl(Map<K, V> outerMap) {
        this.innerMap = outerMap;
    }

    public Map<K, V> getInnerMap() {
        return innerMap;
    }

    @Override
    public void put(K key, V value) {
        this.innerMap.put(key, value);

    }

    @Override
    public Object get(K key) {
        return this.innerMap.get(key);
    }

    @Override
    public boolean contains(K key) {
        return this.innerMap.containsKey(key);
    }

    @Override
    public boolean remove(K key) {
        if (this.innerMap.containsKey(key)) {
            this.innerMap.remove(key);
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
            oos.writeObject(this.innerMap);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }
}