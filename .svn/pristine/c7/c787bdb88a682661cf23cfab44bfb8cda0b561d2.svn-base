package com.fable.dsp.dao.dataswitch.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fable.dsp.common.dto.dataswitch.TimestampDto;
import com.fable.dsp.core.dao.hibernate.UniversalDaoHibernate;
import com.fable.dsp.dao.dataswitch.intf.TimestampDao;
import com.fable.dsp.dmo.dataswitch.TimestampEntity;

@Repository("timestampDao")
public class TimestampDaoHibernate extends UniversalDaoHibernate implements TimestampDao {

    @Override
    public void insert(TimestampEntity timestamp) {
       this.getHibernateTemplate().save(timestamp);
    }

    @Override
    public void update(TimestampEntity timestamp) {
        this.getHibernateTemplate().update(timestamp);
    }

    @Override
    public void delete(TimestampEntity timestamp) {
        this.getHibernateTemplate().delete(timestamp);
    }

    @Override
    public List<TimestampDto> selectTimeStampByCondition(
        TimestampEntity timestamp) {
        // TODO Auto-generated method stub
        return null;
    }

}
