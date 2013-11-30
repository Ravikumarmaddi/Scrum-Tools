package ch.paru.scrumTools.common.reflection;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import ch.paru.scrumTools.common.exception.ToolException;

import com.google.common.collect.Lists;

public class ReflectionUtil {

	private static final String PACKAGENAME = "ch.paru.scrumTools";

	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getSingleClass(Class<T> superClass, Class<? extends Annotation> annotation) {
		Reflections reflections = new Reflections(PACKAGENAME);

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
		Reflections reflections = new Reflections(PACKAGENAME);

		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Custom.class);
		if (annotated == null || annotated.size() <= 0) {
			return null;
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
		else if (clazzes.size() <= 0) {
			return null;
		}
		else {
			throw new ToolException("too many custom implementation found: " + clazzes, null);
		}
	}
}
