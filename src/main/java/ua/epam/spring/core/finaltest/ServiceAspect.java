package ua.epam.spring.core.finaltest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class ServiceAspect {

    @After("execution(* *..Service.update(..))")
    public void m0(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! m0");
    }

    @After("execution(public String *(..))")
    public void m1(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! m1");
    }

    @AfterReturning("execution(* *..Service.update(..))")
    public void m2(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! m2");
    }

    @AfterThrowing("execution(* *..Service.*(..))")
    public void m3(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! m3");
    }

    @Around("execution(public * *..core.*Service.*(..))")
    public void m4(ProceedingJoinPoint jp){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! m4");
    }
}
