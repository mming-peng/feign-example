package top.crossoverjie.feign.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.ming.feign.plus.register.FeignPlusClient;

/**
 * Function:
 *
 *   
 * Date: 2020/7/25 02:54
 * @since JDK 11
 */
@FeignPlusClient(name = "test", url = "${test.url}", port = "${test.port}")
public interface Test {


    @PostMapping("/test")
    String test(@RequestParam("i") Integer i);
}
