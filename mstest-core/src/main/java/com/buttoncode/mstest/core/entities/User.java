package com.buttoncode.mstest.core.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	@Column(name = "first_name", nullable=false)
	private String firstname;

	@Column(name = "last_name")
	private String lastname;

	@Column(nullable=false, unique=true)
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;

	@Column(nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;

	private String passwordResetToken;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	      name="user_role",
	      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private List<Role> roles;

	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="user_department_permission",
			joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="ID")})
	private List<Department> departments;

	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
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

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public List<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	public String getPasswordResetToken()
	{
		return passwordResetToken;
	}
	public void setPasswordResetToken(String passwordResetToken)
	{
		this.passwordResetToken = passwordResetToken;
	}

	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
}
