package com.hun.market.core.zone;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TestZoneNotConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment env = context.getEnvironment();

        if(env.getActiveProfiles() != null && env.getActiveProfiles().length > 0) {
            if(env.getActiveProfiles()[0].equals("test")) {
                return false;
            }
        }

        return true;
    }
}
