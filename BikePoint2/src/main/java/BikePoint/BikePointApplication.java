package BikePoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BikePointApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikePointApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		final int size = 16 * 1024 * 1024;
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
			.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
			.build();
		return WebClient.builder()
			.exchangeStrategies(strategies)
			.build();
}


}
