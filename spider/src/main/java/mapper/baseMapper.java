package mapper;

import java.util.function.Function;

import entity.DeepCopy;

public abstract class baseMapper<T, R> implements Function<T, R> {

	protected abstract R mapper(T data);

	public R copyMapper(T data) {
		T temp = data instanceof DeepCopy ? ((DeepCopy<T>) data).copy() : data;
		return mapper(temp);
	}
}
