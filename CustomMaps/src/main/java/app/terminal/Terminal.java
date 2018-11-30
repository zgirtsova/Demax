package app.terminal;

import app.constants.Paths;
import app.domain.javalibmap.JLibImpl;
import app.domain.javalibmap.JLibMap;
import app.domain.nojavalibmap.NoJLibMap;
import app.domain.oopdesign.Stapler;

import java.util.HashMap;

public class Terminal {

    public static void run() {

        /*
         *  a) Implement the persistent storage – you may use standard
         *  language libraries (e.g. a map implementation in Java).
         *  The storage must be able to survive application restarts;
         *
         *  Create, Save and Retrieve Custom Map
         */
         //  Create and fill
        JLibMap<String, Object> jLibMap = new JLibImpl<>(new HashMap<>());

        jLibMap.put("1", "May be I am working?");
        jLibMap.put("2", "May be I am working?");
        jLibMap.put("3", "Or may be I am not working?");

         //  Save to file
        jLibMap.saveMap();

         //  Retrieve Map from file
        JLibMap<String, Object> newJLibMap = JLibImpl.readMapFromFile();

         //  List items in Map
        JLibImpl.listItemsJLibMap(newJLibMap);


        /*
         * Implement a) without the standard language libraries
         * (e.g. in Java do not use any of the standard provided map implementations); 
         *
         */
         // Create and fill
        NoJLibMap<String, Object> noJLibMap = new NoJLibMap<>();

        noJLibMap.put("1", "May be I work?");
        noJLibMap.put("2", "May be I am working?");
        noJLibMap.put("3", "Or may be I am not working?");


         // Save to file and return String
        String savedMap = noJLibMap.saveMap();
        System.out.println("The serialized object is " + noJLibMap.saveMap());


         //Retrieve Map from file
        NoJLibMap<String, Object> map2 = NoJLibMap.readMapFromFile(Paths.NOJLIBMAP_FILE_NAME);

        /*
         * OOP Design
         * Design and implement an object model of the object in the following picture.
         * Please describe the context, in which the object model should be applied.
         *
         */
        Stapler stapler = new Stapler();
        stapler.refill();
        stapler.stap();

    }

}
