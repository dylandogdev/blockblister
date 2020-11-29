package com.dylandogdev.blockblister.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String address;
    private Date dob;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<RentalEntity> rentals;
}
