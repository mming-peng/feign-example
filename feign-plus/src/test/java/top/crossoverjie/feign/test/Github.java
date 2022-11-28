package top.crossoverjie.feign.test;

import feign.Headers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.ming.feign.plus.register.FeignPlusClient;

import java.util.List;

/**
 * Function:
 *
 *   
 * Date: 2020/7/25 02:54
 * @since JDK 11
 */
@FeignPlusClient(name = "github", url = "${github.url}", port = "${github.port}")
public interface Github {

    @GetMapping("/repos/{owner}/{repo}/contributors")
    @Headers("Content-Type: application/json")
    List<GitHubRes> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
