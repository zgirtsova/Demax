package app.domain.javalib;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JLibImpl<K, V> implements JLibMap<K, V> {


    private Map<K, V> innerMap;


    public JLibImpl(Map<K, V> outerMap) {
        this.innerMap = outerMap;
    }

    public void put(K key, V value) {
        this.innerMap.put(key, value);

    }

    public V get(K key) {
        return this.innerMap.get(key);
    }

    public boolean contains(K key) {
        return this.innerMap.containsKey(key);
    }

    public boolean remove(K key) {
        if (this.innerMap.containsKey(key)) {
            this.innerMap.remove(key);
            return true;
        } else
            return false;
    }

    public Map<K, V> getInnerMap() {
        return this.innerMap;
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

    public static JLibMap<String, Object> readMapFromFile() {


        Map<String, Object> map = null;
        try {
            FileInputStream fis = new FileInputStream("hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (Map) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        System.out.println("Deserialized HashMap..");


        JLibMap<String, Object> theMap = new JLibImpl<>(map);

        return theMap;
    }

    public static void listItemsJLibMap(JLibMap<String, Object> map) {

        // Display content using Iterator
        Set set = map.getInnerMap().entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key: " + mentry.getKey() + " & Value: ");
            System.out.println(mentry.getValue());
        }
    }
}