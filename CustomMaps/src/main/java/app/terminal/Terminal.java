package app.terminal;

import app.domain.javalib.JLibImpl;
import app.domain.javalib.JLibMap;
import app.domain.nojavalib.NoJLibMap;
import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Terminal {

    public static void run() {

        /*
         *  a) Implement the persistent storage – you may use standard
         *  language libraries (e.g. a map implementation in Java).
         *  The storage must be able to survive application restarts;
         *
         *  Create, Save and Retrieve Custom Map
         */

        // Create and fill
        JLibMap<String, Object> jLibMap = new JLibImpl<>(new HashMap<>());

        jLibMap.put("1", "May be I am working?");
        jLibMap.put("2", "May be I am working?");
        jLibMap.put("3", "Or may be I am not working?");
        System.out.println(jLibMap.remove("2"));

        // Save to file
        jLibMap.saveMap();

        //Retrieve Map from file
        JLibMap<String, Object> newMap = readMapFromFile();

        // List items in Map
        listItemsFromMap(newMap);



        /*
         * Implement a) without the standard language libraries
         * (e.g. in Java do not use any of the standard provided map implementations); 
         */


        // Create and fill
        NoJLibMap<String, Object> map = new NoJLibMap<>();

        map.put("1", "May be work?");
        map.put("2", "May be I am working?");
        map.put("3", "Or may be I am not working?");


        // Save to file
        //map.saveMap();

        Gson gson = new Gson();
        String json = gson.toJson(map);
        System.out.println(json);
        NoJLibMap<String, Object> map2 = gson.fromJson(json, NoJLibMap.class);
        System.out.println();

    }
    private static JLibMap<String, Object> readMapFromFile() {


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
    private static void listItemsFromMap(JLibMap<String, Object> map) {
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
