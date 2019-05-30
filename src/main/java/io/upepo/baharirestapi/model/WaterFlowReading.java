package io.upepo.baharirestapi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "water_flow_readings")
public class WaterFlowReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="device_id")
    private String deviceId;

    @Column(name = "meter_id")
    private Long meterId;

    @Column(name="interval_flow")
    private BigDecimal intervalFlow;

    @Column(name = "reading_date")
    private Date readingDate;

    @Column(name="reporting_date")
    private Date reportingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }

    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }
}
