package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "periodic")
public class Periodic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "date_of_start")
    private Date dateofstart;

    @Column(name = "date_of_end")
    private Date dateofend;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Date getDateofstart() {
        return dateofstart;
    }
    public void setDateofstart(Date dateofstart) {
        this.dateofstart = dateofstart;
    }

    public Date getDateofend() {
        return dateofend;
    }
    public void setDateofend(Date dateofend) {
        this.dateofend = dateofend;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
