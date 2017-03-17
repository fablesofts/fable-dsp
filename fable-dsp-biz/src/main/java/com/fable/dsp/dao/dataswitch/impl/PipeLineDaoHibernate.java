package com.fable.dsp.dao.dataswitch.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.dto.dataswitch.AddTableDto;
import com.fable.dsp.common.util.DataBaseTypes;
import com.fable.dsp.common.util.EncryptDES;
import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.dataswitch.intf.PipeLineDao;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TransEntity;

/**
 * 操作子任务的DAO
 * 
 * @author Administrator
 */
@Repository("pipeLineDao")
public class PipeLineDaoHibernate extends GenericDaoHibernate<PipeLine>
    implements PipeLineDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<PipeLine> listPipeLine(final Page page,
        final PipeLine pipeLine) {
        //
        return this.getHibernateTemplate().findByExample(pipeLine);
    }

    @Override
    public PipeLine getPipeLineByConditions(final PipeLine pipeLine) {
        return this.getHibernateTemplate().execute(
            new HibernateCallback<PipeLine>() {
                @Override
                public PipeLine doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    return null;
                }
            });
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageList<PipeLine> findPipeListList(final Page page,
        final PipeLine pipeLine) {
        final String countHql = "select count(*) from PipeLine p";
        final PageList<PipeLine> pageList = new PageList<PipeLine>();
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        final Long count =(Long)session.createQuery(countHql).uniqueResult();
        if (count > 0) {
            // 分页+条件：如果不为空
            @SuppressWarnings("unchecked")
            final List<PipeLine> list =
                	getHibernateTemplate().findByExample(
                    pipeLine == null ? new PipeLine() : pipeLine,
                    page.getIndex(), page.getPageSize());
            page.setCount(count);
            pageList.setList(list);
        }
        else {
            pageList.setList(new ArrayList<PipeLine>());
            page.setCount(0);
        }
        pageList.setPage(page);
        session.close();
        return pageList;
    }

    @Override
    public Long listCount(final Page page, final PipeLine pipeLine) {
        // 查询总记录数的方法
        return this.getHibernateTemplate().execute(
            new HibernateCallback<Long>() {
                @Override
                public Long doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    Long count = null;
                    final StringBuffer sbcount = new StringBuffer();
                    sbcount
                        .append(" select count(p.id) from PipeLine p where 1=1");
                    // 没有拼条件的
                    count =
                        (Long)session.createQuery(sbcount.toString())
                            .uniqueResult();
                    session.close();
                    return count;
                }
            });
    }

    @Override
    public void insert(final PipeLine entity) {
        super.insert(entity);
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(final PipeLine entity) {
        super.delete(entity);
    }

    @Override
    public void update(final PipeLine entity) {
        super.update(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PipeLine> listPipelinesById(final Long id) {
        return this.getHibernateTemplate().executeFind(
            new HibernateCallback<List<PipeLine>>() {

                @Override
                public List<PipeLine> doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    return session
                        .createQuery(
                            " from PipeLine p left join fetch p.taskEntity t where t.id=:id")
                        .setParameter("id", id).list();
                }
            });
    }

    @Override
    public TransEntity getSourceByPId(final Long id) {
        final Session session =
            this.getHibernateTemplate().getSessionFactory().openSession();
        final TransEntity transEntity =
            (TransEntity)session
                .createSQLQuery(
                "SELECT * FROM DSP_TRANS_ENTITY WHERE ID=(SELECT SOURCE_ID FROM DSP_PIPELINE WHERE ID=" +
                id + ") ").addEntity(TransEntity.class)
                .uniqueResult();
        session.close();
        return transEntity;
    }

    @Override
    public TransEntity getTargetById(final Long id) {
        final Session session =
            this.getHibernateTemplate().getSessionFactory().openSession();
        final TransEntity transEntity =
            (TransEntity)session
            .createSQLQuery(
            "SELECT * FROM DSP_TRANS_ENTITY WHERE ID=(SELECT TARGET_ID FROM DSP_PIPELINE WHERE ID=" +
            id + ") ").addEntity(TransEntity.class).uniqueResult();
        session.close();
        return transEntity;
    }

    @Override
    public List<PipeLine> findPipeLineByTaskId(final Long id) {
        final Session session =
            this.getHibernateTemplate().getSessionFactory().openSession();
        List<PipeLine> lines =(List<PipeLine>)session.createSQLQuery(
        "SELECT * FROM DSP_PIPELINE WHERE TASK_ID=" + id)
        .addEntity(PipeLine.class).list();
        session.close();
        return lines;
    }

    @Override
    public List<BigDecimal> getpipidsByTaskId(final Long taskId) {
        final Session session =
            this.getHibernateTemplate().getSessionFactory().openSession();
        final List<BigDecimal> list =
        session.createSQLQuery(
        "SELECT P.ID FROM DSP_PIPELINE P  WHERE P.TASK_ID=" + taskId).list();
        session.close();
        return list;
    }

    @Override
    public List<Character> getEntityByPid(final Long taskId, final Long long1) {
        final Session session = getHibernateTemplate().getSessionFactory().openSession();
        final StringBuilder hql = new StringBuilder();
        hql.append("SELECT e.type FROM dsp_trans_entity e WHERE e.id IN");
        hql.append(" (SELECT p.source_id FROM dsp_pipeline p WHERE p.id=" +
            long1 + " AND p.task_id=" + taskId + ")");
        hql.append(" OR e.id IN");
        hql.append(" (SELECT p.target_id FROM dsp_pipeline p WHERE p.id=" +
            long1 + " AND p.task_id=" + taskId + ")");
        final List<Character> list =
            session.createSQLQuery(hql.toString()).list();
        session.close();
        return list;

    }

    @Override
    public AddTableDto getAddTables(final Long taskId) throws Exception {
        Session session =
            this.getHibernateTemplate().getSessionFactory().openSession();
        StringBuffer selectsource =
            new StringBuffer("SELECT a.id, a.username, a.password,"
                + " a.connect_url, a.db_type,a.db_name, b.table_name")
                .append(" FROM dsp_data_source a, dsp_trans_entity b")
                .append(" WHERE b.id in")
                .append(" (SELECT source_id")
                .append(" FROM dsp_pipeline")
                .append(
                    " WHERE ID in (SELECT ID FROM dsp_pipeline WHERE task_id = " +
                        taskId + "))")
                .append(" and a.ID = (SELECT data_source_id")
                .append(" FROM dsp_trans_entity")
                .append(" WHERE ID in (SELECT source_id")
                .append(" FROM dsp_pipeline")
                .append(" WHERE ID in (SELECT ID")
                .append(" FROM dsp_pipeline")
                .append(" WHERE task_id =" + taskId + ")))");
        List<Object[]> list =
            session.createSQLQuery(selectsource.toString()).list();
        AddTableDto tabledto = new AddTableDto();
        tabledto.setUsername(list.get(0)[1].toString());
        tabledto.setPassword(EncryptDES.decrypt(list.get(0)[2].toString(),
            CommonConstants.DES_KEY));
        tabledto.setConnect_url(list.get(0)[3].toString());

        if (CommonConstants.ORACLE_TYPE.equals(list.get(0)[4].toString())) {
            tabledto.setDbtype(DataBaseTypes.ORACLE);
        } else if(CommonConstants.MYSQL_TYPE.equals(list.get(0)[4].toString())) {
            tabledto.setDbtype(DataBaseTypes.MYSQL);
            tabledto.setDbname(list.get(0)[5].toString());
        } else if(CommonConstants.SQLSERVER_TYPE.equals(list.get(0)[4].toString())) {
            tabledto.setDbtype(DataBaseTypes.SQLSERVER);
            tabledto.setDbname(list.get(0)[5].toString());
        } else if(CommonConstants.DM_TYPE.equals(list.get(0)[4].toString())) {
            tabledto.setDbtype(DataBaseTypes.DM);
            tabledto.setDbname(list.get(0)[5].toString());
        } else if(CommonConstants.DM_TYPE_7.equals(list.get(0)[4].toString())) {
            tabledto.setDbtype(DataBaseTypes.DM7);
            tabledto.setDbname(list.get(0)[5].toString());
        }
        String rebuildTriggerSql = "select rebuild_trigger from dsp_task where id ="+taskId; 
        String rebuildTrigger = session.createSQLQuery(rebuildTriggerSql).toString();
        if(CommonConstants.ZERO.equals(rebuildTrigger)) {
            tabledto.setRebuildTrigger(false);
        } else {
            tabledto.setRebuildTrigger(true);
        }
        
        tabledto.setTablename(list.get(0)[6].toString());
        
        session.close();
        return tabledto;
    }

}
