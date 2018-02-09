package filter;

import entity.DeepCopy;

public abstract class baseFilter<T> {
	public abstract T filter(T data);
}
