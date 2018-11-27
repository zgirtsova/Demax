package app.terminal;

import app.domain.model.MapImpl;
import app.domain.model.MyMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Terminal {

    public static void run() {

        MyMap<Integer, String> theMap = new MapImpl<>();
        theMap.put(1, "May be I am working?");
        theMap.put(2, "May be I am working?");
        theMap.put(3, "May be I am working?");
        theMap.put(1, "May be I am not working?");

        try
        {
            FileOutputStream fos =
                    new FileOutputStream("hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(theMap);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }
}
