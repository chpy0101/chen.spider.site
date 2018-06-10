package chen.spider.spiderservice.handler;

import chen.spider.spiderservice.contact.BaseRequest;
import chen.spider.spiderservice.web.MyRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by chpy on 18/5/18.
 */
public class TestHandler implements HandlerInterceptor {

    ObjectMapper objectMapper = new ObjectMapper()
    {
        {
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            //javaTimeModule.addSerializer()//todo..
            javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            registerModule(javaTimeModule);
        }
    };

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        MyRequestWrapper myRequestWrapper=null;
        if(httpServletRequest instanceof MyRequestWrapper){
            myRequestWrapper = new MyRequestWrapper(httpServletRequest);
        }
        byte[] bytes = new byte[myRequestWrapper.getContentLength()];
        myRequestWrapper.getInputStream().read(bytes);
        BaseRequest response = objectMapper.readValue(new String(bytes), BaseRequest.class);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
