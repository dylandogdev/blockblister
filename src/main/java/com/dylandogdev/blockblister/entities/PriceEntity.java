package com.dylandogdev.blockblister.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="prices")
public class PriceEntity {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Column(name="price_group")
    private String priceGroup;
    @NonNull
    private BigDecimal price;

    public PriceEntity() {};

    public PriceEntity(String priceGroup, BigDecimal price) {
        this.priceGroup = priceGroup;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPriceGroup() {
        return priceGroup;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPriceGroup(String priceGroup) {
        this.priceGroup = priceGroup;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
