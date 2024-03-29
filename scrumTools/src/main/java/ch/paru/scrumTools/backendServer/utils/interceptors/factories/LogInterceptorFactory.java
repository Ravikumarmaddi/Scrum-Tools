package ch.paru.scrumTools.backendServer.utils.interceptors.factories;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import ch.paru.scrumTools.common.logging.ToolLogger;
import ch.paru.scrumTools.common.logging.ToolLoggerFactory;
import ch.paru.scrumTools.backendServer.utils.interceptors.AbstractInterceptor;
import ch.paru.scrumTools.backendServer.utils.interceptors.command.InterceptorCommand;

public class LogInterceptorFactory {

	private static final ToolLogger TIMER = ToolLoggerFactory.getTimeLogger();
	private static final ToolLogger LOGGER = ToolLoggerFactory.getLogger(LogInterceptorFactory.class);

	@SuppressWarnings("unchecked")
	public <T, U extends T> T getInterceptor(Class<T> clazz, U impl) {
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		Class<?>[] proxyInterfaces = new Class[] { clazz };
		InvocationHandler handler = new LoggerHandler(impl);
		Object obj = Proxy.newProxyInstance(sysClassLoader, proxyInterfaces, handler);
		return (T) obj;
	}

	private class LoggerHandler extends AbstractInterceptor {

		private final Object obj;

		private LoggerHandler(Object obj) {
			super();
			this.obj = obj;
		}

		public Object interceptorInvoke(Object proxy, Method method, Object[] args) throws Exception {
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

		@Override
		protected void processInterceptorCommand(InterceptorCommand command) {
		}
	}
}
