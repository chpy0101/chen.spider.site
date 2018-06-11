package chen.spider.spiderservice.handler;

import chen.spider.spiderservice.controller.util.SpringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.net.URI;
import java.text.MessageFormat;

/**
 * Created by chpy on 18/6/6.
 */

public class MyRestTemplate extends RestTemplate {

    private static Field requestField;
    Logger logger = LoggerFactory.getLogger(MyRestTemplate.class);
    private final String requestInfo = "Request url:::::{0} ,method::::: {1}, data::::: {2}";

    /* 
     * 缓存获取的feild对象,减少反射
     * @author chenpengyue
     * @date 18/6/10 上午12:25
     * @param: 
     * @return 
     */
    static {
        Class requestCls = null;
        Class<?>[] cls = RestTemplate.class.getDeclaredClasses();
        for (Class cl : cls) {
            if (cl.getName().equals("org.springframework.web.client.RestTemplate$HttpEntityRequestCallback")) {
                requestCls = cl;
                break;
            }
        }
        try {
            requestField = requestCls.getDeclaredField("requestEntity");
            requestField.setAccessible(true);
        } catch (NoSuchFieldException e) {
        }
    }

    /* 
     * 获取请求对象
     * @author chenpengyue
     * @date 18/6/10 上午12:24
     * @param: [requestCallback]
     * @return org.springframework.http.HttpEntity<?>
     */
    private HttpEntity getRequestEntity(RequestCallback requestCallback) throws IllegalAccessException {
        HttpEntity res = null;
        if (requestField != null)
            res = (HttpEntity) requestField.get(requestCallback);
        return res;
    }

    @Override
    public <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {

        //纪录请求体日志
        preExecute(url, method, requestCallback);
        T response = super.doExecute(url, method, requestCallback, responseExtractor);
        //纪录返回体日志
        //logger.info();
        return response;
    }


    public void preExecute(URI url, HttpMethod method, RequestCallback requestEntity) {
        try {
            ObjectMapper objectMapper = (ObjectMapper) SpringUtils.getBean("myObjectMapper");
            HttpEntity resEntity = getRequestEntity(requestEntity);
            logger.info(MessageFormat.format(requestInfo, url.toString(), method.toString(), objectMapper.writeValueAsString(resEntity.getBody())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
