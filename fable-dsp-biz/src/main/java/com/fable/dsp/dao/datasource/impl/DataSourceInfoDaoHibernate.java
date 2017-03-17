package com.fable.dsp.dao.datasource.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.fable.dsp.core.dao.hibernate.GenericDaoHibernate;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.datasource.intf.DataSourceInfoDao;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.dmo.system.networkcfg.InServer;

@Repository("dataSourceInfoDao")
public class DataSourceInfoDaoHibernate extends GenericDaoHibernate<DataSourceInfo> implements DataSourceInfoDao  {

	/**
	 * 根据条件，查询数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataSourceInfo> listDataSourceInfoListByConditions(
			DataSourceInfo source) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(source);
	}

	/**
	 * 分页查询整表数据(表名：DSP_DATA_SOURCE)
	 */
	@Override
	public PageList<DataSourceInfo> findPageDataSourceInfo(Page pager,
			DataSourceInfo source) {
		PageList<DataSourceInfo> pageDataSource = new PageList<DataSourceInfo>();
		
		Long count = Long.valueOf(this.listDataSourceInfoListByConditions(source).size());
		
		if (count > 0) {
			@SuppressWarnings("unchecked")
			List<DataSourceInfo> list = getHibernateTemplate().findByExample(source == null ? new DataSourceInfo() : source, pager.getIndex(),
					pager.getPageSize());
			pager.setCount(count);
			
			pageDataSource.setList(list);
		} else {
			pageDataSource.setList(new ArrayList<DataSourceInfo>());
			pager.setCount(0);
		}
		pageDataSource.setPage(pager);
		return pageDataSource;
	}

	/**
	 * 获取内交换，内网的网卡
	 */
	@Override
	public String getDrviceType(){
		
		String hql = "from NetworkInnInfo";
		Query sq = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<InServer> list = sq.list();
		System.out.println(list.size());
		return list.get(0).getInCardName();
	}

	@Override
	public boolean getSameSourceName(DataSourceInfo source) {
		String hql = "from DataSourceInfo as d where d.id <> :id and name = :name";
		if(source.getId()==null){
		    hql = "from DataSourceInfo as d where name = :name";
		}
		Query sq = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		if(source.getId()!=null){
		    sq.setParameter("id", source.getId());
		}
		sq.setParameter("name", source.getName());
		List list = sq.list();
		System.out.println("***"+source.getName()+"**List"+list.size()+"****");
		if(list.size()>0)
			return true;
		else
			return false;
		
		
	}
	
	@Override
	public void getOldDataSourceInfo(DataSourceInfo source){
	    getHibernateTemplate().evict(source);
	}

	
	
    @Override
    public String isDelete(Long id) {
        String sql = "SELECT NAME FROM DSP_TASK WHERE ID in (SELECT task_id FROM DSP_PIPELINE WHERE target_id IN (SELECT ID FROM DSP_TRANS_ENTITY WHERE data_source_id = "+id+") OR source_id IN (SELECT ID FROM DSP_TRANS_ENTITY WHERE data_source_id = "+id+")) ORDER BY ID";

        @SuppressWarnings("unchecked")
		List<Object> taskNameList = this.getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql).list();
        
        if(taskNameList.size()>0){
//            StringBuffer deleteMess = new StringBuffer();
//            for(Object taskName : taskNameList){
//                deleteMess.append(taskName+"|");
//                System.out.println(taskName);
//            }
            return "任务[" + taskNameList.get(0).toString()+"]已经配置了此数据源，无法删除！";
            
        }else{
            return null;
        }        
    }

	
	
	

}
