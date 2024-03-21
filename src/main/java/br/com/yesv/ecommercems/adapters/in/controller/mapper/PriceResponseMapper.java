package br.com.yesv.ecommercems.adapters.in.controller.mapper;

import br.com.yesv.ecommercems.adapters.in.controller.dto.PriceResponse;
import br.com.yesv.ecommercems.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(source = "finalPrice", target = "finalPrice", numberFormat = "$#.00")
    PriceResponse toPriceResponse(Price price);

}
