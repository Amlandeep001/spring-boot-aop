package com.example.aop.aspect;

import java.time.Duration;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class EmployeeServiceAspect
{
	// @Pointcut(value="execution(* com.example.aop.service.*.*(..))")
	@Pointcut(value = "within(com.example.aop.service.*)")
	public void loggingPointCut()
	{
		// Do Nothing
	}

	/*@Before(value = "execution(* com.example.aop.service.*.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		
		log.info("Before method:" + joinPoint.getSignature());
	
		startTime = LocalDateTime.now();
		
		if(joinPoint.getArgs().length == 2) {
			
			log.info("Creating Employee started with args: {}, current time: {}", joinPoint.getArgs(), startTime);
			
		}
		else {
			
			log.info("Deleting Employee started with args: {}, current time: {}", joinPoint.getArgs(), startTime);
			
		}
	}
	
	@After(value = "execution(* com.example.aop.service.*.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		
		log.info("After method:" + joinPoint.getSignature());
		
		endTime = LocalDateTime.now();
	
		if(joinPoint.getArgs().length == 2) {
			
		log.info("Successfully created Employee with args: {}, current time: {}", joinPoint.getArgs(), endTime);
		
		}
		else {
			
			log.info("Successfully removed Employee with args: {}, current time: {}", joinPoint.getArgs(), endTime);
			
		}
		
		log.info("Total time taken: {} ms", Duration.between(startTime, endTime).toMillis());
	}*/

	// @Around(value = "execution(* com.example.aop.service.*.*(..))")
	// @Around("loggingPointCut()")
	@Around("target(com.example.aop.service.EmployeeService)")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable
	{
		LocalDateTime startTime = LocalDateTime.now();
		log.info("Before method invoked :: {}, current time :: {}", joinPoint.getSignature().getName(), startTime);

		Object obj = joinPoint.proceed();

		LocalDateTime endTime = LocalDateTime.now();
		log.info("After method invoked :: {}, current time :: {}, duration :: {} ms", joinPoint.getSignature().getName(), endTime, Duration.between(startTime, endTime).toMillis());

		return obj;
	}
}
