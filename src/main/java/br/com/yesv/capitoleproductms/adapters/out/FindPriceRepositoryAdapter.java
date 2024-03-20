package br.com.yesv.capitoleproductms.adapters.out;

import br.com.yesv.capitoleproductms.adapters.out.repository.PriceRepository;
import br.com.yesv.capitoleproductms.adapters.out.repository.mapper.PriceEntityMapper;
import br.com.yesv.capitoleproductms.domain.model.Price;
import br.com.yesv.capitoleproductms.domain.ports.out.FindPriceOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
public class FindPriceRepositoryAdapter implements FindPriceOutputPort {
    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    public FindPriceRepositoryAdapter(PriceRepository priceRepository, PriceEntityMapper priceEntityMapper) {
        this.priceRepository = priceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    public Price find(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        log.info("Searching for price in the database for ProductId: {}, BrandId: {}, ApplicationDate: {}", productId, brandId, applicationDate);
        var price = priceRepository.findTop1ByPk_ProductProductIdAndPk_BrandBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPk_PriorityDesc(productId, brandId, applicationDate, applicationDate);
        log.info("Database result: {}", price);

        return price.isPresent() ? priceEntityMapper.toPrice(price.get()) : null;
    }
}
