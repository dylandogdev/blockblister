package com.dylandogdev.blockblister.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="prices")
public class PriceEntity {

    @Id
    @GeneratedValue
    private int id;
    @Column(name="price_group")
    private String priceGroup;
    private BigDecimal price;

}
