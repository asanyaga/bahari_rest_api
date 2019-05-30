package io.upepo.huizhongmdmimport.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="t_nbiot_flow")
public class MDMMeterData {

    @Id
    @Column(name="nid")
    private Long id;

    @Column(name="cDeviceId")
    private String deviceId;

    @Column(name="nflow")
    private BigDecimal intervalFlow;

    @Column(name = "dtimeOfReading")
    private Date timeOfReading;

    @Column(name="dtimeOfReporting")
    private Date timeOfReporting;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public BigDecimal getIntervalFlow() {
        return intervalFlow;
    }

    public void setIntervalFlow(BigDecimal intervalFlow) {
        this.intervalFlow = intervalFlow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeOfReading() {
        return timeOfReading;
    }

    public void setTimeOfReading(Date timeOfReading) {
        this.timeOfReading = timeOfReading;
    }

    public Date getTimeOfReporting() {
        return timeOfReporting;
    }

    public void setTimeOfReporting(Date timeOfReporting) {
        this.timeOfReporting = timeOfReporting;
    }
}
