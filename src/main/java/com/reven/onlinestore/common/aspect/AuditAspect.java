package com.reven.onlinestore.common.aspect;

import com.reven.onlinestore.common.annotation.ActivityTrace;
import com.reven.onlinestore.common.model.CustomerActivity;
import com.reven.onlinestore.common.repository.CustomerActivityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class AuditAspect {

    private final CustomerActivityRepository activityRepository;

    @Around("@annotation(com.reven.onlinestore.common.annotation.ActivityTrace)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ActivityTrace annotation = method.getAnnotation(ActivityTrace.class);
            Object[] args = joinPoint.getArgs();
            String activityParam = Arrays.stream(args)
                    .map(Object::toString)
                    .collect(Collectors.joining(",","{","}"));
            CustomerActivity activity = new CustomerActivity()
                    .setCustomerName("COMING_SOON")
                    .setCustomerAction(annotation.action())
                    .setParams(activityParam)
                    .setActionTime(Instant.now().toEpochMilli());
            activityRepository.save(activity);
        } catch (Exception ex) {
            log.error("Error when log activity", ex);
        }
        return joinPoint.proceed();
    }
}