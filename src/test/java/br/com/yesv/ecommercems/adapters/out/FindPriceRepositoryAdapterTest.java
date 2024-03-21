package br.com.yesv.ecommercems.adapters.out;

import br.com.yesv.ecommercems.adapters.out.repository.PriceRepository;
import br.com.yesv.ecommercems.adapters.out.repository.entity.*;
import br.com.yesv.ecommercems.adapters.out.repository.mapper.PriceEntityMapper;
import br.com.yesv.ecommercems.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FindPriceRepositoryAdapter.class})
@ExtendWith(SpringExtension.class)
class FindPriceRepositoryAdapterTest {
    @Autowired
    private FindPriceRepositoryAdapter findPriceRepositoryAdapter;

    @MockBean
    private PriceEntityMapper priceEntityMapper;

    @MockBean
    private PriceRepository priceRepository;

    @Test
    void testFind() {
        BrandEntity brand = new BrandEntity();
        brand.setBrandId(1);
        brand.setName("brand");

        CurrencyEntity currency = new CurrencyEntity();
        currency.setCurrencyId("EUR");
        currency.setName("currency");

        PriceListEntity priceList = new PriceListEntity();
        priceList.setId(1);
        priceList.setName("priceList");

        ProductEntity product = new ProductEntity();
        product.setProductId(1);
        product.setName("product");

        PricesPKEntity pk = new PricesPKEntity();
        pk.setBrand(brand);
        pk.setCurrency(currency);
        pk.setPriceList(priceList);
        pk.setPriority(1);
        pk.setProduct(product);

        PricesEntity pricesEntity = new PricesEntity();
        pricesEntity.setEndDate(LocalDate.of(2020, 1, 1).atStartOfDay());
        pricesEntity.setPk(pk);
        pricesEntity.setPrice(10.0d);
        pricesEntity.setStartDate(LocalDate.of(2020, 1, 1).atStartOfDay());
        Optional<PricesEntity> ofResult = Optional.of(pricesEntity);
        when(priceRepository
                .findTop1ByPk_ProductProductIdAndPk_BrandBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPk_PriorityDesc(
                        anyInt(), anyInt(), any(), any()))
                .thenReturn(ofResult);
        Price.PriceBuilder currencyResult = Price.builder().brandId(1).currency("EUR");
        Price.PriceBuilder productIdResult = currencyResult.endApplicationDate(LocalDate.of(2020, 1, 1).atStartOfDay())
                .finalPrice(10.0d)
                .priceList(1)
                .productId(1);
        Price buildResult = productIdResult.startApplicationDate(LocalDate.of(2020, 1, 1).atStartOfDay()).build();
        when(priceEntityMapper.toPrice(any())).thenReturn(buildResult);

        Price actualFindResult = findPriceRepositoryAdapter.find(1, 1, LocalDate.of(2020, 1, 1).atStartOfDay());

        verify(priceRepository)
                .findTop1ByPk_ProductProductIdAndPk_BrandBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPk_PriorityDesc(
                        anyInt(), anyInt(), any(), any());
        verify(priceEntityMapper).toPrice(any());
        LocalTime expectedToLocalTimeResult = actualFindResult.getStartApplicationDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualFindResult.getEndApplicationDate().toLocalTime());
    }

}
