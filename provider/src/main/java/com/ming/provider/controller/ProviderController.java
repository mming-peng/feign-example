package com.ming.provider.controller;

import com.ming.entity.Demo;
import com.ming.api.ProviderApi;
import com.ming.entity.TestDemo;
import com.ming.exception.DemoException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProviderController implements ProviderApi {

    @Override
    public String list(int id) {
        try {
            int i = id / 0;
        } catch (Exception e) {
            throw new DemoException(e.getMessage());
        }
        return "test";
    }

    @Override
    public Demo listDto(Demo demo) {
        return demo;
    }

    @Override
    public String list() {
        JSONObject object = new JSONObject();
        object.put("a", "aa");
        object.put("b", "bb");
        return object.toString();
    }

    @Override
    public String testMap(Map<String, Object> map) {
        Long a = (Long) map.get("a");
        return "OK";
    }

    @Override
    public String test(TestDemo testDemo, String s) {
        System.out.println(testDemo);
        System.out.println(s);
        return "ok";
    }

    @Override
    public String jsonObject(JSONObject object) {
        Long a = (Long) object.get("a");
        System.out.println(a);
        return "OK";
    }

    @Override
    public Map<String, Object> testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1L);
        return map;
    }

    @Override
    public Map<String, String> testMapString() {
        Map<String, String> map = new HashMap<>();
        map.put("a", ":aa");
        return map;
    }

    @Override
    public JSONObject testJSONObject() {
        JSONObject object = new JSONObject();
        object.put("a", 1L);
        return object;
    }

    @Override
    public Map testOMap(Map inMap) {
        System.out.println("ok");
        return null;
    }

    @Override
    public Map<Long, Map<String, Object>> t() {
        Map<Long, Map<String, Object>> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ss", "ss");
        map1.put("sssdss", 1L);
        map.put(1L, map1);
        return map;
    }

    @Override
    public void testRString(String s){
        System.out.println(s.substring(1,s.length()-1));
    }

    @Override
    public List<Map<String, Object>> listDto() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("ss", "ss");
        map.put("sssdss", 1L);
        for (int i = 0 ; i< 100;i++){
            list.add(map);
        }
        return list;
//        return null;
    }

    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("a", 1);
//        map.put("b", 2);
//        map.put("c", "ssssz");
//        System.out.println(map);
        Map<Long, Map<String, Object>> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ss", "ss");
        map1.put("sssdss", 1L);
        map.put(1L, map1);
        Object ob = map;
        if (ob instanceof Map) {
            Map<Object, Object> de = (Map) ob;
            Map change = de.entrySet().stream()
                    .collect(Collectors.toMap(
                            key -> key.getKey(),
                            value -> value.getValue() instanceof Integer ?
                                    Long.valueOf(((int) value.getValue())) : value.getValue()));
            System.out.println(change);
        }
    }


}
