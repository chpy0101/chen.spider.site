package controller;

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
    private Function _callBackMapper;

    public <R> void set_callBackMapper(Function<T, R> _callBackMapper) {
        this._callBackMapper = _callBackMapper;
    }


    public abstract T getData();

    public <R> R getDataThenMapper() throws Exception {
        if (this._callBackMapper == null)
            throw new Exception("mapper为空");
        T result = getData();
        return (R) this._callBackMapper.apply(result);
    }

}
