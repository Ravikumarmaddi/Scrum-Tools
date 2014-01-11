package ch.paru.scrumTools.exchangeServer.utils.interceptors.factories;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import ch.paru.scrumTools.exchangeServer.utils.interceptors.AbstractInterceptor;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.InterceptorCommand;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.InterceptorCommandType;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.LoadCacheCommand;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.StoreCacheCommand;

import com.google.common.collect.Maps;

public class CacheInterceptorFactory {

	@SuppressWarnings("unchecked")
	public <T, U extends T> T getInterceptor(Class<T> clazz, U impl) {
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		Class<?>[] proxyInterfaces = new Class[] { clazz };
		InvocationHandler handler = new CacheHandler(impl);
		Object obj = Proxy.newProxyInstance(sysClassLoader, proxyInterfaces, handler);
		return (T) obj;
	}

	private class CacheHandler extends AbstractInterceptor {

		private final Object obj;

		private Map<String, Object> cache = Maps.newHashMap();

		private CacheHandler(Object obj) {
			super(InterceptorCommandType.STORE_CACHE);
			this.obj = obj;
		}

		public Object interceptorInvoke(Object proxy, Method method, Object[] args) throws Exception {
			String cacheKey = getCacheKey(method, args);

			if (cacheKey == null || !cache.containsKey(cacheKey)) {
				Object result = method.invoke(obj, args);
				cache.put(cacheKey, result);
			}

			return cache.get(cacheKey);
		}

		private String getCacheKey(Method method, Object[] args) {
			if (args == null || args.length == 0) {
				return null;
			}

			StringBuffer sb = new StringBuffer();
			sb.append(method.getName());
			for (Object arg : args) {
				sb.append(arg.toString());
			}
			return sb.toString();
		}

		@Override
		protected void processInterceptorCommand(InterceptorCommand command) {
			switch (command.getType()) {
			case LOAD_CACHE:
				LoadCacheCommand loadCommand = (LoadCacheCommand) command;
				if (loadCommand.loadCache()) {

				}
				return;

			case STORE_CACHE:
				StoreCacheCommand storeCommand = (StoreCacheCommand) command;
				if (storeCommand.storeCache()) {

				}
				return;
			}
		}

	}
}
