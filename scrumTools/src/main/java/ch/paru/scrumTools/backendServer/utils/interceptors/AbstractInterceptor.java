package ch.paru.scrumTools.backendServer.utils.interceptors;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import ch.paru.scrumTools.backendServer.utils.interceptors.command.InterceptorCommand;
import ch.paru.scrumTools.backendServer.utils.interceptors.command.InterceptorCommandType;

import com.google.common.collect.Lists;

public abstract class AbstractInterceptor implements InvocationHandler {

	private Method methodToListenFor;
	private List<InterceptorCommandType> commandTypes;

	protected AbstractInterceptor(InterceptorCommandType... commandTypes) {
		this.commandTypes = Lists.newArrayList(Arrays.asList(commandTypes));
		methodToListenFor = InterceptedService.class.getDeclaredMethods()[0];
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (methodToListenFor.equals(method) //listening to the right method
				&& args.length == 1 // only one argument is possible
				&& commandTypes.contains(((InterceptorCommand) args[0]).getType()) // type has to be correct
		) {
			InterceptorCommand command = (InterceptorCommand) args[0];
			processInterceptorCommand(command);
			return null;
		}
		else {
			return interceptorInvoke(proxy, method, args);
		}
	}

	protected abstract Object interceptorInvoke(Object proxy, Method method, Object[] args) throws Throwable;

	protected abstract void processInterceptorCommand(InterceptorCommand command);
}
