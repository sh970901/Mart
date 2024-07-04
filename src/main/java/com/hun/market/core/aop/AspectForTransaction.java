package com.hun.market.core.aop;

import com.hun.market.core.context.MainContext;
import com.hun.market.core.context.RoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Aspect
@Component
public class AspectForTransaction implements Ordered {

    @Around(MainContext.DATA_SOURCE_AROUND)
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {

        String target = pjp.getSignature().getDeclaringTypeName()+"."+ pjp.getSignature().getName();
        log.info("###################### 트랜잭션 경계 구분 확인 시작 {} {}", target, TransactionSynchronizationManager.isCurrentTransactionReadOnly());


        RoutingDataSource.push(target);

        try {
            return  pjp.proceed();
        } finally {
            log.info("###################### 트랜잭션 경계 구분 확인 종료 {} {}", target, TransactionSynchronizationManager.isCurrentTransactionReadOnly());
            RoutingDataSource.pop();
        }
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
