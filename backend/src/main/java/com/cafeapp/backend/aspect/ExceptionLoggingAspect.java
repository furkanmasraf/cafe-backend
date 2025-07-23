package com.cafeapp.backend.aspect;

import com.cafeapp.backend.model.ErrorLog;
import com.cafeapp.backend.repository.ErrorLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ExceptionLoggingAspect {

    private final ErrorLogRepository errorLogRepo;

    public ExceptionLoggingAspect(ErrorLogRepository errorLogRepo) {
        this.errorLogRepo = errorLogRepo;
    }

    @AfterThrowing(pointcut = "execution(* com.cafeapp.backend.service..*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        ErrorLog log = new ErrorLog();
        log.setExceptionType(ex.getClass().getSimpleName());
        log.setMessage(ex.getMessage());
        log.setClassName(joinPoint.getTarget().getClass().getSimpleName());
        log.setMethodName(joinPoint.getSignature().getName());

        String params = Arrays.toString(joinPoint.getArgs());
        log.setParameters(params);

        errorLogRepo.save(log);

        System.out.println(">> HATA LOG KAYDEDİLDİ: " + ex.getMessage());
    }
}
