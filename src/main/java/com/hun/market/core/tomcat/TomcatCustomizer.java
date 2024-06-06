package com.hun.market.core.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.core.StandardHost;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                //((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
            }
        };
    }
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers((context) -> {
            if (context.getParent() instanceof StandardHost parent) {
                parent.setErrorReportValveClass(CustomErrorRepValve.class.getName());
                parent.addValve(new CustomErrorRepValve());
            }
        });
//        factory.addContextValves();
//        factory.addEngineValves();
        factory.addConnectorCustomizers(connector -> {
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
                AbstractHttp11Protocol<?> protocolHandler = (AbstractHttp11Protocol<?>) connector
                        .getProtocolHandler();
                protocolHandler.setKeepAliveTimeout(80000);
                protocolHandler.setMaxKeepAliveRequests(50);
                protocolHandler.setUseKeepAliveResponseHeader(true);
                protocolHandler.setRelaxedPathChars("[]%");
                protocolHandler.setRelaxedQueryChars("<>[\\\\]^`{|}");
            }
//            connector.setProperty("relaxedQueryChars", "<>[\\]^`{|}");
        });

    }

}
