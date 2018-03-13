package chen.spider.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * 并行任务数据收集类
 *
 * @param <T>
 */
public class ParallelHandler<T> {

	public ParallelHandler() {
		taskList = new ArrayList<>();
		results = new ArrayList<>();
	}

	/**
	 * 并行任务集合
	 */
	private List<Function<Object, T>> taskList;
	/**
	 * 返回结果集合
	 */
	private List<Future<T>> results;
}



