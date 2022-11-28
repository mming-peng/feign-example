package com.ming.consumer.feign;

import com.ming.api.ProviderApi;
import top.ming.feign.plus.register.FeignPlusClient;

@FeignPlusClient(name = "demo", url = "${demo.url}", port = "${demo.port}")
public interface ProviderClient extends ProviderApi {
}
