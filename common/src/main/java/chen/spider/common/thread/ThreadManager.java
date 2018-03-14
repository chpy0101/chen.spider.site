package chen.spider.common.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ThreadManager {
	@Autowired
	private ThreadPoolTaskExecutor executor;

	/**
	 * 执行多线程任务
	 *
	 * @param handler 任务对象
	 * @param <T>
	 */
	public <T> void beginWork(ParallelHandler<T> handler) {
		if (handler == null || handler.getTaskList().size() <= 0) {
			return;
		}
		//线程池
		if (executor == null) {
			executor = new ThreadPoolTaskExecutor();
			executor.setQueueCapacity(1000);
			executor.setMaxPoolSize(30);
			executor.setCorePoolSize(10);
			executor.setKeepAliveSeconds(300);
		}
		handler.getTaskList().forEach(t -> handler.addResult(executor.submit(t)));
	}
}
