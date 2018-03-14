package chen.spider.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	private List<ActionDelegate<T>> taskList;
	/**
	 * 返回结果集合
	 */
	private List<Future<T>> results;

	public boolean addTask(ActionDelegate<T> task) {
		return this.taskList.add(task);
	}

	public boolean addResult(Future<T> result) {
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



