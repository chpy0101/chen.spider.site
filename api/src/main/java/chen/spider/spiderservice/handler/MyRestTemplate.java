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
import java.util.UUID;

/**
 * Created by chpy on 18/6/6.
 */

public class MyRestTemplate extends RestTemplate {

    //错误次数过多时视为日志记录bug停止日志记录
    private static Integer errorItmes = 0;
    private static Field requestField;
    Logger logger = LoggerFactory.getLogger(MyRestTemplate.class);
    private final String requestInfo = "Request reqId:{0} ,url:{1} ,method:{2}, head:{3} ,data:{4}";
    private final String responseInfo = "Response reqId:{0} ,body:{1}";
    private final ObjectMapper objectMapper = (ObjectMapper) SpringUtils.getBean("myObjectMapper");

    /* 
     * 缓存获取的feild对象,减少反射
     * @author chenpengyue
     * @date 18/6/10 上午12:25
     * @param: 
     * @return 
     */
    static {
        try {
            for (Class cl : RestTemplate.class.getDeclaredClasses())
                if (cl.getName().equals("org.springframework.web.client.RestTemplate$HttpEntityRequestCallback")) {
                    requestField = cl.getDeclaredField("requestEntity");
                    requestField.setAccessible(true);
                    break;
                }
        } catch (NoSuchFieldException e) {
        }
    }


    @Override
    public <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        String reqId = UUID.randomUUID().toString().replace("-", "");
        //纪录请求体日志
        recordRequest(reqId, url, method, requestCallback);
        T response = super.doExecute(url, method, requestCallback, responseExtractor);
        //纪录返回体日志
        recordResponse(reqId, response);
        return response;
    }


    private HttpEntity getRequestEntity(RequestCallback requestCallback) throws IllegalAccessException {
        HttpEntity res = null;
        if (requestField != null)
            res = (HttpEntity) requestField.get(requestCallback);
        return res;
    }

    private void recordRequest(String reqId, URI url, HttpMethod method, RequestCallback requestEntity) {
        try {
            HttpEntity resEntity = getRequestEntity(requestEntity);
            String head = resEntity.getHeaders().getContentType().getType();
            logger.info(MessageFormat.format(requestInfo, url.toString(), method.toString(), head, objectMapper.writeValueAsString(resEntity.getBody())));
        } catch (Exception e) {
            errorItmes++;
            e.printStackTrace();
        }
    }

    private <T> void recordResponse(String reqId, T response) {
        try {
            logger.info(MessageFormat.format(responseInfo, reqId, objectMapper.writeValueAsString(response)));
        } catch (Exception ex) {
            errorItmes++;
            ex.printStackTrace();
        }
    }


}
