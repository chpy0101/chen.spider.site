package chen.spider.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	protected static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	public static void debug(String message){
		logger.debug(message);
	}

	public static void debug(String message, Exception ex){
		logger.debug(message, ex);
	}

	public static void info(String message){
		logger.info(message);
	}

	public static void info(String message, Exception ex){
		logger.info(message, ex);
	}

	public static void warn(String message){
		logger.warn(message);
	}

	public static void warn(String message, Exception ex){
		logger.warn(message, ex);
	}

	public static void error(String message){
		logger.error(message);
	}

	public static void error(String message, Exception ex){
		logger.error(message, ex);
	}
}