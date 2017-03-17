package com.fable.dsp.core.annotation;

import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;

@Repository("logAnnotationDao")
public class LogAnnotationDaoImpl extends GenericDaoHibernate<UserLogEntity> implements LogAnnotationDao {
    
}
