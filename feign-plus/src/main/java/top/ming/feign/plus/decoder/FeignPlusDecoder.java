package top.ming.feign.plus.decoder;

import cn.hutool.core.util.StrUtil;
import feign.Response;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import top.ming.feign.plus.context.FeignContextHolder;
import top.ming.feign.plus.log.FeignLogInterceptor;
import top.ming.feign.plus.metrics.Metrics;
import top.ming.feign.plus.springboot.FeignSpringContextHolder;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Function:
 * <p>
 * <p>
 * Date: 2022/2/7 23:46
 *
 * @since JDK 11
 */
@Slf4j
public class FeignPlusDecoder extends JacksonDecoder {

    private Decoder decoder = new Decoder.Default();

    @SneakyThrows
    @Override
    public Object decode(Response response, Type type) {
        Object decode;
        if ("java.lang.String".equals(type.getTypeName())) {
            decode = decoder.decode(response, type);
        } else {
            decode = super.decode(response, type);
        }
        // build target info.
        String interfaceName = response.request().requestTemplate().methodMetadata().targetType().getName();
        String targetMethod = response.request().requestTemplate().methodMetadata().configKey();
        String target = StrUtil.format("{}.{}", interfaceName, targetMethod);
        FeignLogInterceptor logInterceptor = FeignSpringContextHolder.getBean(FeignLogInterceptor.class);
        logInterceptor.response(target, response.request().url(), decode);
        Long start = FeignContextHolder.getLocalTime();
        long end = System.currentTimeMillis();
        Metrics.time(Duration.ofMillis(end - start), "feign_call", "target", target, "status", "success");
        if (decode instanceof Map) {
            Map<Object, Object> map = (Map) decode;
            Map change = map.entrySet().stream()
                    .collect(Collectors.toMap(
                            key -> key.getKey(),
                            value -> value.getValue() instanceof Integer ?
                                    Long.valueOf(((int) value.getValue())) : value.getValue()));
            return change;
        }
        return decode;
    }

}
