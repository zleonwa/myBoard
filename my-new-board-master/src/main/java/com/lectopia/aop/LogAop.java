package com.lectopia.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAop {
	
/*	@Pointcut("within(com.lectopia.board.persistence.*)") // 여기 패키지에 있는 것들을 대상으로 하겠씁니다.
	private void pointcutMethod() {
		
	}
	
	@Before("within(com.lectopia.board.persistence.*)")
	public void beforeMethod() {
		System.out.println("beforeMethod() called...");
	}*/
	
	@Around("execution(* com..controller.*Controller.*(..)) or " +
			"execution(* com..service.*Impl.*(..)) or " +
			"execution(* com..dao.*Mapper.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		String type = joinPoint.getSignature().getDeclaringTypeName();
		String name ="";
		
		if(type.indexOf("Controller") > -1) {
			name = "Controller \t: ";
		} else if(type.indexOf("Service") > -1) {
			name = "ServiceImpl \t: ";
		} else if(type.indexOf("DAO") > -1) {
			name = "DAO \t: ";
		}
	
		System.out.println(name+type+"."+joinPoint.getSignature().getName()+"()");
		return joinPoint.proceed();
	}
}
