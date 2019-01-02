package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stocktaking_software_periodic")
public class StocktakingSoftwarePeriodic extends Stocktaking implements Serializable {

    @Column(name = "key_number")
    private String keynumber;

    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;

    @ManyToOne
    @JoinTable(
            name="stocktaking_software_periodic_periodic",
            joinColumns={@JoinColumn(name="stocktaking_software_periodic_id", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="periodic_id", referencedColumnName="ID")})
    private Periodic periodics;

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

    public Periodic getPeriodics() {
        return periodics;
    }
    public void setPeriodics(Periodic periodics) {
        this.periodics = periodics;
    }
}
