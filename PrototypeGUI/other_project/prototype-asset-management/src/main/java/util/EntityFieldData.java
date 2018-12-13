package util;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityFieldData {
	String columnName();
	Class<?> type();
	int sqlType();
}