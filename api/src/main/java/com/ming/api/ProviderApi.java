package com.ming.api;

import com.ming.entity.Demo;
import com.ming.entity.TestDemo;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @name ProviderApi
 * @date 2020-10-12 00:54
 **/
@RequestMapping("/aa")
public interface ProviderApi {

    @RequestMapping("/provider/list")
    String list(@RequestParam("id") int id);

    @PostMapping("/provider/test")
    List<Map<String, Object>> listDto();

    @PostMapping("/provider/testBody")
    Demo listDto(@RequestBody Demo demo);

    @RequestMapping("/string")
    String list();

    @PostMapping("/provider/map")
    String testMap(@RequestBody Map<String, Object> map);

    @PostMapping("/test")
    String test(@RequestBody TestDemo testDemo, @RequestParam String s);

    @PostMapping("/test/json")
    String jsonObject(@RequestBody JSONObject object);

    @PostMapping("/testMap")
    Map<String, Object> testMap();

    @PostMapping("/testMapString")
    public Map<String, String> testMapString();

    @PostMapping("/testJson")
    JSONObject testJSONObject();

    @PostMapping("/testOMap")
    Map testOMap(@RequestBody Map inMap);

    @PostMapping("/t")
    Map<Long, Map<String, Object>> t();

    @PostMapping("/RString")
    void testRString(@RequestBody String s);

}
