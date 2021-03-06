package chen.spider.spiderservice.mapper;

import java.util.function.Function;

import chen.spider.spiderservice.entity.DeepCopy;

public abstract class baseMapper<T, R> implements Function<T, R> {

	protected abstract R mapper(T data);

	public R copyMapper(T data) {
		T temp = data instanceof DeepCopy ? ((DeepCopy<T>) data).copy() : data;
		return mapper(temp);
	}

	@Override
	public R apply(T t) {
		return copyMapper(t);
	}
}

