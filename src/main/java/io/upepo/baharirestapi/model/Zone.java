package io.upepo.baharirestapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotBlank(message = "Zone name must be entered")
    private String name;

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
}
