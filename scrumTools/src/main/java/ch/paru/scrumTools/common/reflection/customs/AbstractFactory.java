package ch.paru.scrumTools.common.reflection.customs;

import java.lang.reflect.Constructor;

import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;

public abstract class AbstractFactory {

	protected <T extends Customizable> Class<? extends T> getClassToUse(Class<T> customClass) {
		return getClassToUseWithDefault(customClass, customClass);
	}

	protected <T extends Customizable> Class<? extends T> getClassToUseWithDefault(Class<T> customClass,
			Class<? extends T> defaultClass) {
		Class<? extends T> clazz = ReflectionUtil.getCustomClass(customClass);
		if (clazz == null) {
			clazz = defaultClass;
		}
		return clazz;
	}

	protected <T extends Customizable> T getInstance(Class<? extends T> instanceClass, Class<?>[] constructorTypes,
			Object[] params) {
		try {
			Constructor<? extends T> constructor = instanceClass.getConstructor(constructorTypes);
			return constructor.newInstance(params);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}

	protected <T extends Customizable, P> T getInstance(Class<? extends T> instanceClass, P param) {
		try {
			Class<?> clazz = getCustomizedBaseClass(param.getClass());
			Constructor<? extends T> constructor = instanceClass.getConstructor(clazz);
			return constructor.newInstance(param);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}

	protected <T extends Customizable> T getInstance(Class<? extends T> instanceClass) {
		try {
			return instanceClass.newInstance();
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}

	private Class<?> getCustomizedBaseClass(Class<?> baseClazz) {
		if (!Customizable.class.isAssignableFrom(baseClazz)) {
			return baseClazz;
		}

		Class<?>[] interfaces = baseClazz.getInterfaces();
		for (Class<?> clazz : interfaces) {
			if (Customizable.class.equals(clazz)) {
				return baseClazz;
			}
		}
		return getCustomizedBaseClass(baseClazz.getSuperclass());
	}
}
