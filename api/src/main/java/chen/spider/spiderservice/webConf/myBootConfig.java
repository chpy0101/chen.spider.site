package chen.spider.spiderservice.webConf;

import chen.spider.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
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
    @PostConstruct
    public void initDateTimeFormat() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer != null && initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            //时间参数转换器
            genericConversionService.addConverter(new Converter<String, Date>() {
                @Override
                public Date convert(String s) {
                    return TryConvertTime(s);
                }
            });
        }
    }

    private Date TryConvertTime(String s) {
        Date time = null;
        if (s.contains("-") && s.lastIndexOf("-") != s.indexOf("-")) {
            time = DateUtil.convertStringToDate(s, s.indexOf(":") <= 0 ? DateUtil.YYYYMMDD : DateUtil.YYYYMMDDHHMMSS);
        }
        return time;
    }

}
