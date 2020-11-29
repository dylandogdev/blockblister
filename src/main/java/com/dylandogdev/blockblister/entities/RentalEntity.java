package com.dylandogdev.blockblister.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "rentals")
public class RentalEntity {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "media")
    private MediaEntity media;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;
    @Column(name = "rental_date")
    private Date rentalDate;
    @Column(name = "due_date")
    private Date dueDate;
    @Column(name = "return_date")
    private Date returnDate;
    private BigDecimal total;
}
