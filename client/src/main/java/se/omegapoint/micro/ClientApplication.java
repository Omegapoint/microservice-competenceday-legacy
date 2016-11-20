package se.omegapoint.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class ClientApplication {

	@Configuration
	public static class Lol {
		@Bean
		public RestTemplate restTemplate(LoadBalancerClient loadBalancerClient) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getInterceptors().add(new LoadBalancerInterceptor(loadBalancerClient));
			return restTemplate;
		}

		@Bean
		public List<RestTemplate> templates(RestTemplate restTemplate) {
			return Arrays.asList(restTemplate);

		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
