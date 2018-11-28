package app.domain.nojavalib;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {

        MyMap<String, Object> map = new MyMap<>();

        map.put("1", "May be I am working?");

        Gson gson = new Gson();
        String json = gson.toJson(map);
        System.out.println(json);
    }
}
