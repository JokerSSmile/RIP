package models;

import java.util.Date;

public class Car {
    private String model;
    private String subModel;
    private int price;
    private Date year;

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

    public final Date getYear() {
        return new Date(year.getTime());
    }

    public final void setYear(Date value) {
        this.year = new Date(value.getTime());
    }
}
