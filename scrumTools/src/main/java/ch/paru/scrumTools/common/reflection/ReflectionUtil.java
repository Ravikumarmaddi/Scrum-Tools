package ch.paru.scrumTools.common.reflection;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.customs.Custom;

import com.google.common.collect.Lists;

public class ReflectionUtil {

	private static Reflections reflections;
	static {
		// performance improvment as the classpath will only be scanned once
		reflections = new Reflections();
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getSingleClass(Class<T> superClass, Class<? extends Annotation> annotation) {
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(annotation);
		if (annotated == null || annotated.size() <= 0) {
			throw new ToolException("no annotated class found", null);
		}

		List<Class<T>> clazzes = Lists.newArrayList();
		for (Class<?> clazz : annotated) {
			if (superClass.isAssignableFrom(clazz)) {
				clazzes.add((Class<T>) clazz);
			}
		}

		if (clazzes.size() == 1) {
			return clazzes.get(0);
		}
		else {
			throw new ToolException("not exactly 1 class found: " + clazzes, null);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getCustomClass(Class<T> superClass) {
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Custom.class);
		if (annotated == null || annotated.size() <= 0) {
			return null;
		}

		List<Class<T>> clazzes = Lists.newArrayList();
		for (Class<?> clazz : annotated) {
			if (superClass.equals(clazz.getAnnotation(Custom.class).type())) {
				clazzes.add((Class<T>) clazz);
			}
		}

		if (clazzes.size() == 1) {
			return clazzes.get(0);
		}
		else if (clazzes.size() <= 0) {
			return null;
		}
		else {
			throw new ToolException("too many custom implementation found: " + clazzes, null);
		}
	}

}
