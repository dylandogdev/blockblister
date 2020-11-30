package com.dylandogdev.blockblister.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="conditions")
public class ConditionEntity {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String condition;

    public ConditionEntity() {};

    public ConditionEntity(String condition) {
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
