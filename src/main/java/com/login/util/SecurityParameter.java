package com.login.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecurityParameter {
	/**
	 * 是否加密 默认false 加密时传值为true
	 * 
	 * @return
	 */

	boolean encode() default false;

	/**
	 * 是否解密 默认为false， 解密时传值为true
	 * 
	 * @return
	 */
	boolean decode() default false;
}
