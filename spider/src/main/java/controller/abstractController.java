package controller;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Created by chpy on 18/1/27.
 */
public abstract class abstractController<T> {

	private abstractController() {
	}

	public abstractController(boolean enableLoop) {
		this._enableLoop = enableLoop;
	}

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


	public abstract T getData();

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

}
