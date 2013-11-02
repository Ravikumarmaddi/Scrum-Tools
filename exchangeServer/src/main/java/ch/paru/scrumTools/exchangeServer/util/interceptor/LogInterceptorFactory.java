package ch.paru.scrumTools.exchangeServer.util.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;

import ch.paru.scrumTools.exchangeServer.util.logging.EwsLogger;

public class LogInterceptorFactory {

	private static final Logger TIMER = EwsLogger.getTimeLogger();
	private static final Logger LOGGER = EwsLogger.getLogger(LogInterceptorFactory.class);

	@SuppressWarnings("unchecked")
	public <T, U extends T> T getInterceptor(Class<T> clazz, U impl) {
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		Class<?>[] proxyInterfaces = new Class[] { clazz };
		InvocationHandler handler = new LoggerHandler(impl);
		Object obj = Proxy.newProxyInstance(sysClassLoader, proxyInterfaces, handler);
		return (T) obj;
	}

	private class LoggerHandler implements InvocationHandler {

		private final Object obj;

		private LoggerHandler(Object obj) {
			this.obj = obj;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
			long start = System.currentTimeMillis();
			Object result = method.invoke(obj, args);
			long end = System.currentTimeMillis();

			TIMER.debug(method.getName() + " [" + (end - start) + "ms]");
			LOGGER.info(method.getName() + " (" + arrayToString(args) + ")");

			return result;
		}

		private String arrayToString(Object[] args) {
			StringBuffer sb = new StringBuffer();

			for (int i = 0; args != null && i < args.length; i++) {
				if (i > 0) {
					sb.append(", ");
				}

				sb.append(args[i]);
			}
			return sb.toString();
		}

	}
}
