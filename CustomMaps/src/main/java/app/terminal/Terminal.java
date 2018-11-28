package app.terminal;

import app.domain.javalib.MapImpl;
import app.domain.javalib.MyMap;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Terminal {

    public static void run() {

        /**
         *  a) Implement the persistent storage – you may use standard
         *  language libraries (e.g. a map implementation in Java).
         *  The storage must be able to survive application restarts;
         *
         *  Create, Save and Retrieve Custom Map
         */


        MyMap<String, Object> theMap = new MapImpl<>(new HashMap<>());

        theMap.put("1", "May be I am working?");
        theMap.put("2", "May be I am working?");
        theMap.put("3", "May be I am working?");
        theMap.put("4", "Or may be I am not working?");

        theMap.saveMap();










        MyMap<String, Object> newMap = readMapFromFile();

        listItemsFromMap(newMap);

    }
    private static MyMap<String, Object> readMapFromFile() {


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


        MyMap<String, Object> theMap = new MapImpl<>(map);

        return theMap;
    }
    private static void listItemsFromMap(MyMap<String, Object> map) {
        // Display content using Iterator
        Set set = map.getMyMap().entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key: " + mentry.getKey() + " & Value: ");
            System.out.println(mentry.getValue());
        }
    }

}
