package chen.spider.common.thread;

import chen.spider.common.LogUtil;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadManager {
	//线程池
	private static ThreadPoolTaskExecutor executor;

	protected static ThreadPoolTaskExecutor getExecutor() {
		//线程池
		if (executor == null) {
			executor = new ThreadPoolTaskExecutor();
			executor.setQueueCapacity(1000);
			executor.setMaxPoolSize(30);
			executor.setCorePoolSize(10);
			executor.setKeepAliveSeconds(300);
		}
		return executor;
	}

		public static void init(int corePoolSize, int maxPoolSize, int queueSize, int aliveSec) {
		if (executor == null)
			executor = new ThreadPoolTaskExecutor();
		executor.setQueueCapacity(queueSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setCorePoolSize(corePoolSize);
		executor.setKeepAliveSeconds(aliveSec);
	}

	/**
	 * 执行多线程任务
	 *
	 * @param handler 任务对象
	 * @param <T>
	 */
	public static <T> void beginWork(ThreadHandler<T> handler) {
		if (handler == null || handler.getTaskList().size() <= 0) {
			return;
		}

		handler.getTaskList().forEach(t -> {
			try {
				handler.addResult(getExecutor().submit(t));
			} catch (TaskRejectedException ex) {
				LogUtil.error("多线程：" + t.toString(), ex);
			}
		});
	}
}
