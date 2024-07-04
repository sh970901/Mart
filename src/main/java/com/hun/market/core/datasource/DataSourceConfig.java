package com.hun.market.core.datasource;

import com.hun.market.core.context.MainContext;
import com.hun.market.core.zone.TestZoneNotConditional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties(MainContext.CONFIGRURATION_PROPERTIES_PREFIX)
public class DataSourceConfig extends AbstractDataSourceConfig {
    @Bean
    @Conditional(TestZoneNotConditional.class)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
        @Qualifier("routingDataSource") DataSource dataSource, JpaProperties jpaProperties) {

        return builder.dataSource(dataSource)
                      .packages(getPackages().toArray(String[]::new))
                      .properties(jpaProperties.getProperties())
                      .build();
    }

}
