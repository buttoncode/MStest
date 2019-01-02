package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "stocktaking_software")
public class StocktakingSoftware extends Stocktaking implements Serializable {

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "stocktaking_number_sap")
    private String stocktakingnumbersap;

    @Column(name = "stocktaking_number_ms")
    private String stocktakingnumberms;

    @Column(name = "key_number")
    private String keynumber;

    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;

    @ManyToOne
    @JoinTable(
            name="stocktaking_software_employee",
            joinColumns={@JoinColumn(name="stocktaking_software_id", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="employee_id", referencedColumnName="ID")})
    private Employee employees;

    @ManyToMany(mappedBy="stocktakingSoftwares")
    private List<StocktakingHardware> stocktakingHardware;

    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getStocktakingnumbersap() {
        return stocktakingnumbersap;
    }
    public void setStocktakingnumbersap(String stocktakingnumbersap) {
        this.stocktakingnumbersap = stocktakingnumbersap;
    }

    public String getStocktakingnumberms() {
        return stocktakingnumberms;
    }
    public void setStocktakingnumberms(String stocktakingnumberms) {
        this.stocktakingnumberms = stocktakingnumberms;
    }

    public String getKeynumber() {
        return keynumber;
    }
    public void setKeynumber(String keynumber) {
        this.keynumber = keynumber;
    }

    public Employee getEmployees() {
        return employees;
    }
    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public Software getSoftware() {
        return software;
    }
    public void setSoftware(Software software) {
        this.software = software;
    }

    public List<StocktakingHardware> getStocktakingHardware() {
        return stocktakingHardware;
    }
    public void setStocktakingHardware(List<StocktakingHardware> stocktakingHardware) {
        this.stocktakingHardware = stocktakingHardware;
    }
}
