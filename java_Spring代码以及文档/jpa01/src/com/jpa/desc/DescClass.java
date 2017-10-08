package com.jpa.desc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface DescClass {
	public String name() default "";
	public String des() default "";
}
