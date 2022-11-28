package top.ming.feign.plus.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import top.ming.feign.plus.springboot.FeignSpringContextHolder;

import java.time.Duration;

/**
 * Function:
 *
 *   
 * Date: 2022/4/27 23:19
 * @since JDK 11
 */
public class Metrics {
    public static void time(Duration duration, String name, String... tags) {
        MeterRegistry registry = FeignSpringContextHolder.getBean(MeterRegistry.class);
        registry.timer(name, tags).record(duration);
    }

    public static void count(String name, String... tags) {
        MeterRegistry registry = FeignSpringContextHolder.getBean(MeterRegistry.class);
        registry.counter(name, tags).increment();
    }
}
