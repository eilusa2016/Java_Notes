package com.jpa.desc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface DescMethod {
	public String name() default "";
	public String des() default "";
	public String returns() default "";
}
