package com.ming.consumer.controller;

import com.ming.entity.Demo;
import com.ming.entity.Test2;
import com.ming.consumer.feign.ProviderClient;
import com.ming.entity.Test1;
import com.ming.entity.TestDemo;
import com.ming.exception.DemoException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @name ConsumerController
 * @date 2020-10-11 23:45
 **/
@RestController
public class ConsumerController {

    @Resource
    private ProviderClient providerClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/callProvider")
    public String callProvider() throws Exception {
        try {
            String list = providerClient.list(0);
            return list;
        } catch (DemoException e) {
            System.out.println(e.getCode() + "  :" + e.getMessage() + "  :" + e.getAppName());
        }
        return "error";
    }

    @RequestMapping("/consumer/test")
    public List<Map<String, Object>> test() throws Exception {
        List<Map<String, Object>> list = providerClient.listDto();
        return list;
    }

    @RequestMapping("/test")
    public String testBody() throws Exception {
        Demo demo = new Demo(1L, "test");
        Demo s = providerClient.listDto(demo);
        return s.toString();
    }

    @RequestMapping("/test/json")
    public String testJson() {
        return providerClient.list();
    }

    @RequestMapping("/test/map")
    public void testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1L);
        providerClient.testMap(map);
    }

    @RequestMapping("/test/map/a")
    public void testMapRest() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1L);
        restTemplate.postForObject("http://127.0.0.1:8092/aa/provider/map",map,String.class);
    }
    @RequestMapping("/testdemo")
    public void TestDemo(){
        Test1 test = new Test2("sss",1L);
        TestDemo testDemo = new TestDemo(test,"bbbbb");
        providerClient.test(testDemo,"nh");
    }

    @RequestMapping("/json")
    public void jsonObject(){
        JSONObject object = new JSONObject();
        object.put("a",1L);
//        providerClient.jsonObject(object);
        restTemplate.postForObject("http://127.0.0.1:8092/aa/test/json",object,String.class);
    }
    @RequestMapping("/testMap")
    public void testMapA(){
        Map<String,Object> map = providerClient.testMap();
        Long a = (Long) map.get("a");
        System.out.println(a);
    }

    @RequestMapping("/testMapString")
    public void testMapString(){
        Map<String,String> map = providerClient.testMapString();
        String a = map.get("a");
        System.out.println(a);
    }

    @PostMapping("/testJson")
    public void testJSONObject(){
        System.out.println(providerClient.testJSONObject());
        JSONObject map = providerClient.testJSONObject();
        Long a = (Long) map.get("a");
    }

    @PostMapping("/testOMap")
    void testOMap(){
        final Map<String,String> map = new HashMap<>();
        map.put("tt","tt");
        providerClient.testOMap(map);
    }

    @PostMapping("/t")
    Map<Long,Map<String,Object>> t(){
        return providerClient.t();
    }

    @PostMapping("/RString")
    void testRString(){
        providerClient.testRString("errorCode=30000&message=所属应用下已经存在此用户");
    }

}
