package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "stocktaking_hardware")
public class StocktakingHardware extends Stocktaking implements Serializable {

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "stocktaking_number_sap")
    private String stocktakingnumbersap;

    @Column(name = "stocktaking_number_ms")
    private String stocktakingnumberms;

    @Column(name = "product_name")
    private String productname;

    @Column(name = "serial_number")
    private String serialnumber;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="stocktaking_hardware_software",
            joinColumns={@JoinColumn(name="stocktaking_hardware_id", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="stocktaking_software_id", referencedColumnName="ID")})
    private List<StocktakingSoftware> stocktakingSoftwares;

    @ManyToOne
    @JoinTable(
            name="stocktaking_hardware_employee",
            joinColumns={@JoinColumn(name="stocktaking_hardware_id", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="employee_id", referencedColumnName="ID")})
    private Employee employees;

    @ManyToMany
    @JoinTable(
            name="stocktaking_software_periodic_hardware",
            joinColumns={@JoinColumn(name="stocktaking_hardware_id", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="stocktaking_software_periodic_id", referencedColumnName="ID")})
    private List<StocktakingSoftwarePeriodic> stocktakingSoftwarePeriodics;

    @OneToMany
    @JoinTable(
            name="stocktaking_software_device_hardware",
            joinColumns={@JoinColumn(name="stocktaking_hardware_id", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="stocktaking_software_device_id", referencedColumnName="ID")})
    private List<StocktakingSoftwareDevice> stocktakingSoftwareDevices;

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

    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getSerialnumber() {
        return serialnumber;
    }
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public List<StocktakingSoftware> getStocktakingSoftwares() {
        return stocktakingSoftwares;
    }
    public void setStocktakingSoftwares(List<StocktakingSoftware> stocktakingSoftwares) {
        this.stocktakingSoftwares = stocktakingSoftwares;
    }

    public Employee getEmployees() {
        return employees;
    }
    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public List<StocktakingSoftwarePeriodic> getStocktakingSoftwarePeriodics() {
        return stocktakingSoftwarePeriodics;
    }
    public void setStocktakingSoftwarePeriodics(List<StocktakingSoftwarePeriodic> stocktakingSoftwarePeriodics) {
        this.stocktakingSoftwarePeriodics = stocktakingSoftwarePeriodics;
    }

    public List<StocktakingSoftwareDevice> getStocktakingSoftwareDevices() {
        return stocktakingSoftwareDevices;
    }
    public void setStocktakingSoftwareDevices(List<StocktakingSoftwareDevice> stocktakingSoftwareDevices) {
        this.stocktakingSoftwareDevices = stocktakingSoftwareDevices;
    }
}
