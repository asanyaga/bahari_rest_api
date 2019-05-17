package io.upepo.baharirestapi.model;

import javax.persistence.Entity;
import  javax.persistence.Table;
import javax.persistence.EntityListeners;
import  javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.util.Date;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message="First Name must not be blank")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message="Last name must not be blank")
    private String lastName;

    @Column(name="phone")
    private String phone;

    @Column(name = "email_address", nullable = false)
    @Email(message = "Email must be valid")
    private String email;
    @Column(name = "username", nullable = false)
    @NotBlank(message = "User name must not be blank")
    private String username;
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password must not be blank")
    private String password;

    @Column(name = "usertype", nullable = false)
    @NotBlank(message = "User Type must not be blank")
    private String usertype;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Date createdAt;
    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;
    @Column(name = "updated_by", nullable = false)
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "isenabled")
    private boolean isenabled;
    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets created at.
     *
     * @return the created at
     */

    public void setUserName(String username)
    {
        this.username = username;
    }

    public String getUserName()
    {
        return this.username;
    }

    public void setUserType(String usertype)
    {
        this.usertype = usertype;
    }

    public String getUserType()
    {
        return this.usertype;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    /**
     * Gets created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Sets created by.
     *
     * @param createdBy the created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    /**
     * Gets updated by.
     *
     * @return the updated by
     */
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**
     * Sets updated by.
     *
     * @param updatedBy the updated by
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIsEnabled() {
        return isenabled;
    }

    public void setIsEnabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
}
