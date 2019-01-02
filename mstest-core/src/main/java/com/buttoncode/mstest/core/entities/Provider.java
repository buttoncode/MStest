package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String street;

    @Column(name = "post_code")
    private String postcode;

    private String city;

    @Column(name = "nip_number")
    private String nipnumber;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "provider")
    private List<Delivery> deliveries;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getNipnumber() {
        return nipnumber;
    }
    public void setNipnumber(String nipnumber) {
        this.nipnumber = nipnumber;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}
