package io.upepo.baharirestapi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="meter_reading_track")
public class MeterReadingTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="meter_id")
    private Long meterId;

    @Column(name="last_report_date")
    private Date lastReportDate;

    @Column(name="last_reading_date")
    private Date lastReadingDate;

    public  MeterReadingTracker(Long id, Long meterid, Date lastreportdate)
    {
        this.id=id;
        this.meterId = meterid;
        this.lastReportDate =lastreportdate;
    }

    public MeterReadingTracker()
    {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Date getLastReportDate() {
        return lastReportDate;
    }

    public void setLastReportDate(Date lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    public Date getLastReadingDate() {
        return lastReadingDate;
    }

    public void setLastReadingDate(Date lastReadingDate) {
        this.lastReadingDate = lastReadingDate;
    }
}
