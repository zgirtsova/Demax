package app.domain.nojavalibmap;

import app.constants.Paths;
import app.io.BufferReader;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class NoJLibMap<K, V> {

    private float loadfactor = 0.75f;
    private int size;
    private int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    private MyEntry<K, V>[] values = new MyEntry[DEFAULT_CAPACITY];

    public void put(K key, V value) {
        boolean insert = true;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i].setValue(value);
                insert = false;
            }
        }
        if (insert) {
            ensureCapa();
            values[size++] = new MyEntry<K, V>(key, value);
        }
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null) {
                if (values[i].getKey().equals(key)) {
                    return values[i].getValue();
                }
            }
        }
        return null;
    }

    public boolean contains(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i] = null;
                size--;
                condenseArray(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    private void condenseArray(int start) {
        for (int i = start; i < size; i++) {
            values[i] = values[i + 1];
        }
    }

    private void ensureCapa() {
        if (size * loadfactor == values.length) {
            int newSize = values.length * 2;
            values = Arrays.copyOf(values, newSize);
        }
    }

    public String saveMap () {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try (PrintStream out = new PrintStream(new FileOutputStream(Paths.NOJLIBMAP_FILE_NAME))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static NoJLibMap<String, Object> readMapFromFile(String filename) {

        String jsonMap = BufferReader.readFile(filename);
        Gson gson = new Gson();
        NoJLibMap<String, Object> map2 = gson.fromJson(jsonMap, NoJLibMap.class);

        return  map2;

    }
}
