package br.com.yesv.ecommercems.adapters.out.repository.mapper;

import br.com.yesv.ecommercems.adapters.out.repository.entity.PricesEntity;
import br.com.yesv.ecommercems.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    @Mapping(target = "productId", source = "pk.product.productId")
    @Mapping(target = "brandId", source = "pk.brand.brandId")
    @Mapping(source = "startDate", target = "startApplicationDate")
    @Mapping(source = "endDate", target = "endApplicationDate")
    @Mapping(source = "pk.priceList.id", target = "priceList")
    @Mapping(source = "pk.currency.currencyId", target = "currency")
    @Mapping(source = "price", target = "finalPrice")
    Price toPrice(PricesEntity entity);

}