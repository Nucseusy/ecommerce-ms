package br.com.yesv.capitoleproductms.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Product query API for the CAPITOLE application process",
        version = "1.0.0"))
public class OpenAPIConfiguration {

}