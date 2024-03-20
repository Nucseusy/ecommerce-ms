package br.com.yesv.capitoleproductms.domain.model;

import java.time.LocalDateTime;


public class Price {
    private Integer productId;

    private Integer brandId;

    private LocalDateTime startApplicationDate;

    private LocalDateTime endApplicationDate;

    private Integer priceList;

    private String currency;

    private Double finalPrice;

    public Price() {
    }

    public Price(Integer productId, Integer brandId, LocalDateTime startApplicationDate, LocalDateTime endApplicationDate, Integer priceList, String currency, Double finalPrice) {
        this.productId = productId;
        this.brandId = brandId;
        this.startApplicationDate = startApplicationDate;
        this.endApplicationDate = endApplicationDate;
        this.priceList = priceList;
        this.currency = currency;
        this.finalPrice = finalPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartApplicationDate() {
        return startApplicationDate;
    }

    public void setStartApplicationDate(LocalDateTime startApplicationDate) {
        this.startApplicationDate = startApplicationDate;
    }

    public LocalDateTime getEndApplicationDate() {
        return endApplicationDate;
    }

    public void setEndApplicationDate(LocalDateTime endApplicationDate) {
        this.endApplicationDate = endApplicationDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "Price{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", startApplicationDate=" + startApplicationDate +
                ", endApplicationDate=" + endApplicationDate +
                ", priceList=" + priceList +
                ", currency='" + currency + '\'' +
                ", finalPrice=" + finalPrice +
                '}';
    }
}
