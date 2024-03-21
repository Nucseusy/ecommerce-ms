package br.com.yesv.ecommercems.domain.ports.out;

import br.com.yesv.ecommercems.domain.model.Price;

import java.time.LocalDateTime;

public interface FindPriceOutputPort {

    Price find(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
