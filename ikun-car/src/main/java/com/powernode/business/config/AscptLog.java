package com.powernode.business.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 自定义切面类  @Aspect  @Component
 */
@Component
@Aspect
@Slf4j
public class AscptLog {

    @Pointcut("execution(* com.powernode.business.service.impl..*(..))" )
    public void pointcut() {
        System.out.println("com.powernode.business.service.impl.*" );

    }

    //前置通知
    //细节   pointcut() pointcut 名称
    @Before(value = "pointcut()" )
    public void before(JoinPoint joinPoint) {

        //获取当前  切入点的类的方法
        System.out.println("before........." );
        System.out.println("当前调用了哪个类:" + joinPoint.getTarget().getClass().getName());
//        Method [] methods=joinPoint.getTarget().getClass().getMethods();
//        for(int i=0;i<methods.length;i++){
//            System.out.println("方法个数:"+methods.length+"名称:"+methods[i].getName());
//        }

        //类 定义的方法
//        Method [] methods=joinPoint.getTarget().getClass().getDeclaredMethods();
//        for(int i=0;i<methods.length;i++){
//            System.out.println("方法个数:"+methods.length+"名称:"+methods[i].getName());
//        }
        //当前调用了那个方法

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); //获取当前切入点的那个对象
//        System.out.println("method:"+methodSignature.getName());


        //@Slf4j
        //写日志
        log.info("调用了类:" + joinPoint.getTarget().getClass().getName());
        log.info("调用了类的" + methodSignature.getName() + "方法" );


    }


    /**
     * 异常通知
     *
     * @param ex
     */
    @AfterThrowing(value = "pointcut()", throwing = "ex" )
    public void afterThrowing(Throwable ex) {
//        System.out.println("异常通知" + ex.getMessage());
        log.error("异常通知" + ex.getMessage());
    }
}
