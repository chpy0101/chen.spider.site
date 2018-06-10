package chen.spider.spiderservice.webConf;

import chen.spider.common.DateUtil;
import chen.spider.spiderservice.handler.MyRestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Configuration
@EnableConfigurationProperties(ThreadConfigSetting.class)
public class myBootConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("test:::::::::::::sss:::::::::::::::::::::::::::::::::::::11111:::::::::::::::");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);

    }

    /* 
     * 初始化时间解释器
     * @author chenpengyue
     * @date 18/4/16 下午11:17
     * @param: []
     * @return void
     */
    //@PostConstruct
    public void initDateTimeFormat() {
//        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
//        if (initializer != null && initializer.getConversionService() != null) {
//            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
//            //时间参数转换器
//            genericConversionService.addConverter(new Converter<String, Date>() {
//                @Override
//                public Date convert(String s) {
//                    return TryConvertTime(s);
//                }
//            });
//        }
    }

    private Date TryConvertTime(String s) {
        Date time = null;
        if (s.contains("-") && s.lastIndexOf("-") != s.indexOf("-")) {
            time = DateUtil.convertStringToDate(s, s.indexOf(":") <= 0 ? DateUtil.YYYYMMDD : DateUtil.YYYYMMDDHHMMSS);
        }
        return time;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new TestHandler()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(createMapper());
        return converter;
    }

    @Bean("myObjectMapper")
    public ObjectMapper createMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    @Bean
    public MyRestTemplate createRestTemplate(){
        return new MyRestTemplate();
    }

}
