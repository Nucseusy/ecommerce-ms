package br.com.yesv.capitoleproductms.domain.ports.in;

import br.com.yesv.capitoleproductms.domain.model.Price;

import java.time.LocalDateTime;

public interface FindPriceInputPort {
    Price find(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
