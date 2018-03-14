package chen.spider.common.thread;

/**
 * 类似c#委托
 */
@FunctionalInterface
public interface ActionDelegate<T> {
	T run();
}
