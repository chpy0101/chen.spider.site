package chen.spider.spiderservice.controller.webConf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableConfigurationProperties(ThreadConfigSetting.class)
public class myBootConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ThreadConfigSetting threadConfigSetting;

	/**
	 * 线程池配置
	 *
	 * @return
	 */
	@Bean(name = "executor")
	public ThreadPoolTaskExecutor getExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(threadConfigSetting.getCorepool());
		executor.setKeepAliveSeconds(threadConfigSetting.getAlives());
		executor.setMaxPoolSize(threadConfigSetting.getMaxpool());
		executor.setQueueCapacity(threadConfigSetting.getQueuesize());
		return executor;
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("test:::::::::::::sss:::::::::::::::::::::::::::::::::::::11111:::::::::::::::");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);

	}
}
