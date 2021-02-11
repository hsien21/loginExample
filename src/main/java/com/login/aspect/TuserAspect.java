package com.login.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TuserAspect {

	@Pointcut(value = "execution(* com.login.controller..*.*(..))")
	public void testPoint() {

	}

	@Before(value = "testPoint()")
	public void testBefore() {
		System.out.println("-------切麵Before開始--------");

		System.out.println("-------切麵Before結束--------");
	}

	@After(value = "testPoint()")
	public void testAfter() {
		System.out.println("-------切麵After開始--------");

		System.out.println("-------切麵After結束--------");
	}
}
