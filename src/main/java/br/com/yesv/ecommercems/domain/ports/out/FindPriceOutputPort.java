package br.com.yesv.ecommercems.domain.ports.out;

import br.com.yesv.ecommercems.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FindPriceOutputPort {

    Optional<Price> find(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
