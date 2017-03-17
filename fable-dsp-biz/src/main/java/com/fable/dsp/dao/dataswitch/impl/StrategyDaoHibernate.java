package com.fable.dsp.dao.dataswitch.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.dao.dataswitch.intf.StrategyDao;
import com.fable.dsp.dmo.dataswitch.EtlStrategy;
@Repository("strategyDao")
public class StrategyDaoHibernate extends GenericDaoHibernate<EtlStrategy> implements StrategyDao{

    @Override
    public Boolean deleteEtlByPipeLineId(Long id) {
        try {
            Session session =
                this.getHibernateTemplate().getSessionFactory()
                    .openSession();
            session.createSQLQuery(
                "DELETE FROM dsp_etl_strategy WHERE PIPELINE_ID=" + id)
                .executeUpdate();
            session.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
