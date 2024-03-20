package br.com.yesv.capitoleproductms.domain.ports.out;

import br.com.yesv.capitoleproductms.domain.model.Price;

import java.time.LocalDateTime;

public interface FindPriceOutputPort {

    Price find(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
