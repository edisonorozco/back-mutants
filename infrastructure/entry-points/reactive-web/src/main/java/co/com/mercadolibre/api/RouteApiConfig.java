package co.com.mercadolibre.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/api-route.properties")
public class RouteApiConfig {
}
