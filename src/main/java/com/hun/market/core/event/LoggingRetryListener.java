package com.hun.market.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingRetryListener implements RetryListener {
    @Override public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        log.info("Retrying...");
        return RetryListener.super.open(context, callback);
    }

    @Override public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.info("Retry completed");
        RetryListener.super.close(context, callback, throwable);
    }

    @Override public <T, E extends Throwable> void onSuccess(RetryContext context, RetryCallback<T, E> callback, T result) {
        log.info("success occurred during retry");
        RetryListener.super.onSuccess(context, callback, result);
    }

    @Override public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.info("Error occurred during retry: " + throwable.getMessage());
        RetryListener.super.onError(context, callback, throwable);
    }
}
