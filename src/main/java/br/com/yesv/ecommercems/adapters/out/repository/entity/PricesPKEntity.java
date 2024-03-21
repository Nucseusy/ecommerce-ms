package br.com.yesv.ecommercems.adapters.out.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PricesPKEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "PRICE_LIST")
    private PriceListEntity priceList;

    @Column(name = "PRIORITY")
    private Integer priority;

    @ManyToOne
    @JoinColumn(name = "CURR")
    private CurrencyEntity currency;

}
