package com.buttoncode.mstest.admin.web.models;

import com.buttoncode.mstest.core.entities.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class MyAccountForm {

    @Column(nullable=false, unique=true)
    private Integer id;

    @Column(nullable=false, unique=true)
    @NotEmpty
    private String email;

    @Column(nullable=false, unique=true)
    @NotEmpty
    private String firstname;

    @Column(nullable=false, unique=true)
    @NotEmpty
    private String lastname;

    @Column(nullable=false, unique=true)
    private String password;

    @Column(nullable=false)
    @NotEmpty
    private String oldPassword;

    @Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String new1Password;

    @Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String new2Password;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNew1Password() {
        return new1Password;
    }
    public void setNew1Password(String new1Password) {
        this.new1Password = new1Password;
    }

    public String getNew2Password() {
        return new2Password;
    }
    public void setNew2Password(String new2Password) {
        this.new2Password = new2Password;
    }

    public User toUser (){
        User u = new User();
        u.setId(getId());
        u.setFirstname(getFirstname());
        u.setLastname(getLastname());
        u.setEmail(getEmail());
        u.setPassword(getPassword());
        return u;
    }
}
