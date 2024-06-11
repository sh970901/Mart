package com.hun.market;

import com.hun.market.core.web.MarketRunApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarketApplication {

	public static void main(String[] args) {
		MarketRunApplication.run(MarketApplication.class, args);
	}

}
