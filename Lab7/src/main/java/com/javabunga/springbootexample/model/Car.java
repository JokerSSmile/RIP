package com.javabunga.springbootexample.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String model;
    private String subModel;
    private int price;
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date date;

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }


    public final String getModel() {
        return model;
    }

    public final void setModel(String value) {
        this.model = value;
    }

    public final String getSubModel() {
        return subModel;
    }

    public final void setSubModel(String value) {
        this.subModel = value;
    }

    public final int getPrice() {
        return price;
    }

    public final void setPrice(int value) {
        this.price = value;
    }

    public final Date getDate() {
        if (date == null) {
            return null;
        }

        return new Date(date.getTime());
    }

    public final void setDate(Date value) {
        this.date = new Date(value.getTime());
    }
}
