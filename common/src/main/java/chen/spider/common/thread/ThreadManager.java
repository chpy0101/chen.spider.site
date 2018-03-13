package chen.spider.common.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ThreadManager {
	@Autowired
	private ThreadPoolTaskExecutor executor;
}
