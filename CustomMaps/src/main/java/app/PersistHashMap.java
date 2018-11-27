package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class PersistHashMap {
    public static void main(String[] args) {

        boolean read = false;
        if (read == false) {
            write();
        } else {
            Map<Integer, String> map = read();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println("key:" + entry.getKey() + "----- value:" + entry.getValue());
            }
        }

    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, String> read() {
        Map<Integer, String> hmap = new HashMap<Integer, String>();

        try (FileInputStream fis = new FileInputStream("hasmap.txt")) {

            try (ObjectInputStream ois = new ObjectInputStream(fis)) {

                hmap = (HashMap<Integer, String>) ois.readObject();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hmap;
    }

    public static void write() {
        Map<Integer, String> hmap = new HashMap<Integer, String>();
        // Adding elements to HashMap
        hmap.put(1, "1");
        hmap.put(2, "2");
        hmap.put(3, "3");
        hmap.put(4, "4");
        hmap.put(5, "5");

        try (FileOutputStream fos = new FileOutputStream("hashmap.txt")) {

            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(hmap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}