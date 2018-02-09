package controller;

import filter.baseFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Created by chpy on 18/1/27.
 */
public abstract class abstractController<T> {

	private List<baseFilter> _filters;
	/**
	 * 是否启用循环模式
	 */
	private boolean _enableLoop = false;

	/**
	 * 回调mapper
	 */
	private Function _callBackFunction;

	public <R> void set_callBackFunction(Function<T, R> _callBack) {
		this._callBackFunction = _callBack;
	}

	private abstractController() {
		this._filters = new ArrayList<>();
	}

	public abstractController(boolean enableLoop) {
		this();
		this._enableLoop = enableLoop;
	}

	public abstract T getData();

	/**
	 * 添加过滤器
	 *
	 * @param filter
	 */
	public void addFilter(baseFilter filter) {
		this._filters.add(filter);
	}

	/**
	 * 获取数据并执行回调函数(异步执行getData)
	 *
	 * @param <R>
	 * @return
	 * @throws Exception
	 */
	public <R> R getDataThenCallBackAsync() throws Exception {
		if (this._callBackFunction == null)
			throw new Exception("mapper为空");
		CompletableFuture<R> future = CompletableFuture.supplyAsync(() -> {
			return getData();
		}).thenApply((t) -> {
			return (R) _callBackFunction.apply(t);
		});
		return future.get();
	}

	/**
	 * 过滤数据
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	public <T> T filterData(T data) {
		if (this._filters.size() > 0)
			for (baseFilter filter : this._filters) {
				data = (T) filter.filter(data);
			}
		return data;
	}
}
