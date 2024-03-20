package br.com.yesv.capitoleproductms.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "${api.doc.name}",
        version = "${api.doc.version}", description = "${api.doc.description}"))
public class OpenAPIConfiguration {

}