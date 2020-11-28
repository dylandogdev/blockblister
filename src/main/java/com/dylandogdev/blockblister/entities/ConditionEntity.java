package com.dylandogdev.blockblister.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="conditions")
public class ConditionEntity {

    @Id
    @GeneratedValue
    private int id;
    private String condition;
}
