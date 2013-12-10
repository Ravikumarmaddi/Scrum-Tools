package ch.paru.scrumTools.common.reflection.customs;

import java.lang.reflect.Constructor;

import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;

public abstract class AbstractFactory {

	protected <T extends Customizable> Class<? extends T> getClassToUse(Class<T> customClass) {
		Class<? extends T> clazz = ReflectionUtil.getCustomClass(customClass);
		if (clazz == null) {
			clazz = customClass;
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
			Constructor<? extends T> constructor = instanceClass.getConstructor(param.getClass());
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
}
