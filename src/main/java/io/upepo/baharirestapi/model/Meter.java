package io.upepo.baharirestapi.model;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;

import java.util.Date;

@Entity
@Table(name="meters")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="meter_number")
    @NotBlank(message="Meter Number must be entered")
    private String meterNumber;

    @Column(name="device_number")
    private String deviceNumber;

    @Column(name="imei_number")
    private String imeiNumber;

    @Column(name="size")
    private String size;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="model")
    private String model;

    @Column(name="type")
    private String type;

    @Column(name="reset_value")
    private BigDecimal resetValue;

    @Column(name="installation_date")
    private Date installationDate;

    @Column(name="latitude")
    private BigDecimal latitude;

    @Column(name="longitude")
    private BigDecimal longitude;

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getResetValue() {
        return resetValue;
    }

    public void setResetValue(BigDecimal resetValue) {
        this.resetValue = resetValue;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
