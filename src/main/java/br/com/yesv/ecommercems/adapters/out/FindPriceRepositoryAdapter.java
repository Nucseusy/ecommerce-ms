package br.com.yesv.ecommercems.adapters.out;

import br.com.yesv.ecommercems.adapters.out.repository.PriceRepository;
import br.com.yesv.ecommercems.adapters.out.repository.mapper.PriceEntityMapper;
import br.com.yesv.ecommercems.domain.model.Price;
import br.com.yesv.ecommercems.domain.ports.out.FindPriceOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


@Component
@Slf4j
public class FindPriceRepositoryAdapter implements FindPriceOutputPort {
    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    public FindPriceRepositoryAdapter(PriceRepository priceRepository, PriceEntityMapper priceEntityMapper) {
        this.priceRepository = priceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    public Optional<Price> find(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        log.info("Searching for price in the database for ProductId: {}, BrandId: {}, ApplicationDate: {}", productId, brandId, applicationDate);
        var price = priceRepository.findTop1ByPk_ProductProductIdAndPk_BrandBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPk_PriorityDesc(productId, brandId, applicationDate, applicationDate)
                .map(priceEntityMapper::toPrice);

        log.info("Database result: {}", price.map(Objects::toString).orElse("Price not found in the database"));

        return price;
    }
}
