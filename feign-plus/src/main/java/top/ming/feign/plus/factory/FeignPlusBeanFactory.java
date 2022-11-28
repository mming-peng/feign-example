package top.ming.feign.plus.factory;

import feign.Client;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;
import top.ming.feign.plus.contract.SpringMvcContract;
import top.ming.feign.plus.decoder.FeignExceptionDecoder;
import top.ming.feign.plus.decoder.FeignPlusDecoder;
import top.ming.feign.plus.intercept.FeignInterceptor;
import top.ming.feign.plus.springboot.FeignPlusConfigurationProperties;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Function:
 *
 *   
 * Date: 2020/7/25 02:20
 * @since JDK 11
 */
public class FeignPlusBeanFactory<T> implements FactoryBean<T>, ApplicationContextAware, EmbeddedValueResolverAware {


    private ApplicationContext applicationContext;

    private Class<T> proxyInterface ;

    private String url ;

    private StringValueResolver stringValueResolver;

    @Override
    public T getObject() throws Exception {
        FeignPlusConfigurationProperties conf = applicationContext.getBean(FeignPlusConfigurationProperties.class) ;
        Client client ;
        try {
            client = applicationContext.getBean("client", Client.class) ;
        }catch (NoSuchBeanDefinitionException e){
            throw new NullPointerException("Without one of [okhttp3] client") ;
        }
        String resolveUrl = stringValueResolver.resolveStringValue(url);
        T target = Feign.builder()
                .contract(new SpringMvcContract())
                .requestInterceptor(new FeignInterceptor())
                .client(client)
                .encoder(new JacksonEncoder())
                .decoder(new FeignPlusDecoder())
                .errorDecoder(new FeignExceptionDecoder())
                .retryer(new Retryer.Default(100, SECONDS.toMillis(1), 0))
                .options(new Request.Options(conf.getConnectTimeout(),conf.getReadTimeout(), true))
                .target(proxyInterface, resolveUrl);

        return target;
    }

    @Override
    public Class<?> getObjectType() {
        return proxyInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;
    }

    public Class<T> getProxyInterface() {
        return proxyInterface;
    }

    public void setProxyInterface(Class<T> proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver= resolver;
    }
}
