package chen.spider.common.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * 并行任务数据收集类
 *
 * @param <T>
 */
public class ThreadHandler<T> {

	public ThreadHandler() {
		taskList = new Vector<>();
		results = new Vector<>();
	}

	/**
	 * 并行任务集合
	 */
	private List<ActionDelegate<T>> taskList;
	/**
	 * 返回结果集合
	 */
	private List<Future<T>> results;

	public boolean addTask(ActionDelegate<T> task) {
		return this.taskList.add(task);
	}

	protected boolean addResult(Future<T> result) {
		return this.results.add(result);
	}

	public List<Callable<T>> getTaskList() {
		return taskList.stream().map(t ->
				(Callable<T>) () -> t.run()
		).collect(Collectors.toList());
	}

	public List<Future<T>> getResults() {
		return results;
	}
}



