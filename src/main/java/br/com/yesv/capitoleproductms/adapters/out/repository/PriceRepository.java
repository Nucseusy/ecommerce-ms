package br.com.yesv.capitoleproductms.adapters.out.repository;

import br.com.yesv.capitoleproductms.adapters.out.repository.entity.PricesEntity;
import br.com.yesv.capitoleproductms.adapters.out.repository.entity.PricesPKEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PricesEntity, PricesPKEntity> {

    Optional<PricesEntity> findTop1ByPk_ProductProductIdAndPk_BrandBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPk_PriorityDesc(Integer productId, Integer brandId, LocalDateTime startApplicationDate, LocalDateTime endApplicationDate);
}
