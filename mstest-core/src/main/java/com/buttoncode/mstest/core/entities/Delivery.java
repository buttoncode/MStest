package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @Column(name = "fv_number")
    private String fvnumber;

    @Column(name = "date_of_bought")
    private Date dateofbought;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "delivery")
    private List<StocktakingHardware> stocktakingHardwares;

    public Provider getProvider() { return provider;}
    public void setProvider(Provider provider) {this.provider = provider;}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFvnumber() {
        return fvnumber;
    }
    public void setFvnumber(String fvnumber) {
        this.fvnumber = fvnumber;
    }

    public Date getDateofbought() {
        return dateofbought;
    }
    public void setDateofbought(Date dateofbought) {
        this.dateofbought = dateofbought;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<StocktakingHardware> getStocktakingHardwares() {
        return stocktakingHardwares;
    }
    public void setStocktakingHardwares(List<StocktakingHardware> stocktakingHardwares) {
        this.stocktakingHardwares = stocktakingHardwares;
    }
}
