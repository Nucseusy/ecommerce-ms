package br.com.yesv.ecommercems.domain.ports.in;

import br.com.yesv.ecommercems.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FindPriceInputPort {
    Optional<Price> find(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
