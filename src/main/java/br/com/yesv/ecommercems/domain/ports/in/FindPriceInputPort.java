package br.com.yesv.ecommercems.domain.ports.in;

import br.com.yesv.ecommercems.domain.model.Price;

import java.time.LocalDateTime;

public interface FindPriceInputPort {
    Price find(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
