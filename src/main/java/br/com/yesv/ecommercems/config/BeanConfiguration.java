package br.com.yesv.ecommercems.config;

import br.com.yesv.ecommercems.domain.ports.in.FindPriceInputPort;
import br.com.yesv.ecommercems.domain.ports.out.FindPriceOutputPort;
import br.com.yesv.ecommercems.domain.usecase.FindPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public FindPriceInputPort findPriceInputPort(FindPriceOutputPort findPriceOutputPort){
        return new FindPriceUseCase(findPriceOutputPort);
    }
}
