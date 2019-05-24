package io.upepo.huizhongmdmimport.model;


import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="t_residentialdetaild")
public class MDMMeterData {

    @Id
    @Column(name="HDD_ID")
    private Long id;

    @Column(name="HDD_MeterCode")
    private String meterCode;

    @Column(name="HDD_CumFlux")
    private BigDecimal cummulativeFlow;

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public BigDecimal getCummulativeFlow() {
        return cummulativeFlow;
    }

    public void setCummulativeFlow(BigDecimal cummulativeFlow) {
        this.cummulativeFlow = cummulativeFlow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
