package com.ayyovei.aop.retry.aspect;

import com.ayyovei.aop.retry.annotation.Retry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author chenxiaobin
 * @description
 * @create 2019/6/23
 */
@Aspect
@Component
public class RetryAspect {

    private Logger logger = LoggerFactory.getLogger(RetryAspect.class);

    @Around("@annotation(com.ayyovei.aop.retry.annotation.Retry)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        Method method = getMethod(pjp);
        Retry retry = method.getAnnotation(Retry.class);

        int maxRetry = retry.maxRetry();
        if (maxRetry <= 1) {
            return pjp.proceed();
        }

        int alreadyTry = 0;
        while (alreadyTry++ < maxRetry) {
            try {
                return pjp.proceed();
            } catch (Exception e) {
                logger.info("already try {} times,exception is ", alreadyTry, e);
            }
        }

        throw new Throwable("超出最大重试次数");


    }

    public Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = pjp.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        return currentMethod;
    }
}
