package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stocktaking_software_device")
public class StocktakingSoftwareDevice extends Stocktaking implements Serializable {

    @Column(name = "key_number")
    private String keynumber;

    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;

    @ManyToOne
    @JoinTable(
            name="stocktaking_software_device_delivery",
            joinColumns={@JoinColumn(name="stocktaking_software_device_id", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="delivery_id", referencedColumnName="ID")})
    private Delivery delivery;

    public String getKeynumber() {
        return keynumber;
    }
    public void setKeynumber(String keynumber) {
        this.keynumber = keynumber;
    }

    public Software getSoftware() {
        return software;
    }
    public void setSoftware(Software software) {
        this.software = software;
    }

    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
