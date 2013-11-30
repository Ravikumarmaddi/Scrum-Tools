package ch.paru.scrumTools.common.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Custom {
	Class<? extends Customizable> type();
}
