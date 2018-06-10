package chen.spider.spiderservice.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by chpy on 18/5/29.
 */
@Aspect
@Component
public class RestTemplateAdvice {

    @Pointcut(value = "execution(* org.springframework.web.client.RestTemplate.*(..))")
    private void point() {
    }

    @Before("point()")
    public void record() {
        HttpRequestEntity httpRequestEntity = (HttpRequestEntity) null;
        //HttpEntity body = httpRequestEntity.getBody();
    }
}
