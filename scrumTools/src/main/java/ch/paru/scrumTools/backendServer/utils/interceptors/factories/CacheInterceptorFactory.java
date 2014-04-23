package ch.paru.scrumTools.backendServer.utils.interceptors.factories;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import ch.paru.scrumTools.backendServer.utils.interceptors.AbstractInterceptor;
import ch.paru.scrumTools.backendServer.utils.interceptors.command.InterceptorCommand;
import ch.paru.scrumTools.backendServer.utils.interceptors.command.InterceptorCommandType;
import ch.paru.scrumTools.backendServer.utils.interceptors.command.LoadCacheCommand;
import ch.paru.scrumTools.backendServer.utils.interceptors.command.StoreCacheCommand;

import com.google.common.collect.Maps;

public class CacheInterceptorFactory {

	@SuppressWarnings("unchecked")
	public <T, U extends T> T getInterceptor(Class<T> clazz, U impl) {
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		Class<?>[] proxyInterfaces = new Class[] { clazz };
		InvocationHandler handler = new CacheHandler(clazz.getSimpleName(), impl);
		Object obj = Proxy.newProxyInstance(sysClassLoader, proxyInterfaces, handler);
		return (T) obj;
	}

	private class CacheHandler extends AbstractInterceptor {

		private final Object obj;
		private final String name;

		private Map<String, Object> cache = Maps.newHashMap();

		private CacheHandler(String name, Object obj) {
			super(InterceptorCommandType.STORE_CACHE, InterceptorCommandType.LOAD_CACHE);
			this.name = name.toLowerCase();
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
			StringBuffer sb = new StringBuffer();
			sb.append(method.getName());
			if (args != null) {
				for (Object arg : args) {
					sb.append(arg.toString());
				}
			}
			return sb.toString();
		}

		@Override
		protected void processInterceptorCommand(InterceptorCommand command) {
			switch (command.getType()) {
			case LOAD_CACHE:
				LoadCacheCommand loadCommand = (LoadCacheCommand) command;
				loadCommand.loadData(name, cache);
				break;

			case STORE_CACHE:
				StoreCacheCommand storeCommand = (StoreCacheCommand) command;
				storeCommand.storeData(name, cache);
				break;
			}
		}

	}
}
