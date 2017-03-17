package com.fable.dsp.dmo.dataswitch;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DSP_TIMESTAMP")
public class TimestampEntity {
    
    @Id
    private TimestampPK timestampPK;
    
    @Column(name="SWITCH_TIME")
    private Date switchTime;
    
    @Column(name="DATA_COLUMN")
    private String dataColumn;

    public TimestampPK getTimestampPK() {
        return timestampPK;
    }

    public void setTimestampPK(TimestampPK timestampPK) {
        this.timestampPK = timestampPK;
    }

    public Date getSwitchTime() {
        return switchTime;
    }

    public void setSwitchTime(Date switchTime) {
        this.switchTime = switchTime;
    }

    public String getDataColumn() {
        return dataColumn;
    }
    
    public void setDataColumn(String dataColumn) {
        this.dataColumn = dataColumn;
    }
}
