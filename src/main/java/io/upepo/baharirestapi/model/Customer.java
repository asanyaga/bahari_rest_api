package io.upepo.baharirestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotBlank(message = "Customer name must be entered")
    private String name;

    @Column(name="customer_type")
    @NotBlank(message = "Customer name must be entered")
    String customertype;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name="id_number")
    @NotBlank(message="Customer type must be entered")
    String idNumber;

    @Column(name="pin_number")
    @NotBlank(message = "KRA PIN must be entered")
    String pinnumber;

    @Column(name="postal_address")
    String postaladdress;

    @Column(name="postal_code")
    String postalcode;

    @Column(name="phone")
    String phone;

    @Email(message = "Email format must be valid")
    @Column(name="email")
    String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties({"zone", "customer"})
    private List<Connection> connections;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerType()
    {
        return this.customertype;
    }

    public void setCustomerType(String type)
    {
        this.customertype=type;
    }

    public String getIdNumber()
    {
        return this.idNumber;
    }

    public void setIdNumber(String number)
    {
        this.idNumber =number;
    }

    public String getPinNumber()
    {
        return this.pinnumber;
    }

    public void setPinNumber(String number)
    {
        this.pinnumber=number;
    }

    public String getPostalAddress()
    {
        return this.postaladdress;
    }

    public void setPostalAddress(String address)
    {
        this.postaladdress=address;
    }

    public String getPostalCode()
    {
        return this.postalcode;
    }

    public void setPostalCode(String code)
    {
        this.postalcode= code;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
