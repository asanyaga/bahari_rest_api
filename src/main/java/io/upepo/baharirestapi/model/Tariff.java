package io.upepo.baharirestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotBlank(message = "Tariff name must be entered")
    private String name;

    @OneToMany(mappedBy = "tariff", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("tariff")
    private List<TariffBand> tariffBands = new ArrayList<>();

    @Column(name= "isactive")
    private boolean isActive;

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

    public List<TariffBand> getTariffBands() {
        return tariffBands;
    }

    public void setTariffBands(List<TariffBand> tariffBands) {
        this.tariffBands = tariffBands;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
