package br.com.yesv.capitoleproductms.domain.usecase;

import br.com.yesv.capitoleproductms.domain.model.Price;
import br.com.yesv.capitoleproductms.domain.ports.in.FindPriceInputPort;
import br.com.yesv.capitoleproductms.domain.ports.out.FindPriceOutputPort;

import java.time.LocalDateTime;


public class FindPriceUseCase implements FindPriceInputPort {
    private final FindPriceOutputPort findPriceOutputPort;

    public FindPriceUseCase(FindPriceOutputPort findPriceOutputPort) {
        this.findPriceOutputPort = findPriceOutputPort;
    }

    public Price find(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return findPriceOutputPort.find(productId, brandId, applicationDate);
    }
}
