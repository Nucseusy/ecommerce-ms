package br.com.yesv.ecommercems.domain.usecase;

import br.com.yesv.ecommercems.domain.model.Price;
import br.com.yesv.ecommercems.domain.ports.in.FindPriceInputPort;
import br.com.yesv.ecommercems.domain.ports.out.FindPriceOutputPort;

import java.time.LocalDateTime;
import java.util.Optional;


public class FindPriceUseCase implements FindPriceInputPort {
    private final FindPriceOutputPort findPriceOutputPort;

    public FindPriceUseCase(FindPriceOutputPort findPriceOutputPort) {
        this.findPriceOutputPort = findPriceOutputPort;
    }

    public Optional<Price> find(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return findPriceOutputPort.find(productId, brandId, applicationDate);
    }
}
