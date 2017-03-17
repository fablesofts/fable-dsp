package com.fable.dsp.service.dataswitch.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.dao.dataswitch.intf.PipeLineDao;
import com.fable.dsp.dao.dataswitch.intf.TaskDao;
import com.fable.dsp.dao.dataswitch.intf.TransEntityDao;
import com.fable.dsp.dmo.dataswitch.TransEntity;
import com.fable.dsp.service.dataswitch.intf.TransEntityService;
@Service("transEntityService")
public class TransEntityServiceImpl implements TransEntityService{
	
	@Autowired
	TransEntityDao transEntityDao;
	
	public void insert(TransEntity entity) {
		// TODO Auto-generated method stub
		transEntityDao.insert(entity);
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		transEntityDao.deleteById(id);
	}

	
	public void delete(TransEntity entity) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 通过name查找id
	 * @param name
	 * @return
	 */
	@Override
	public Long getIdByName(String name){
		//return transEntityDao.get
		return this.transEntityDao.getIdByName(name);
	}
	@Override
	public void update(TransEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransEntity getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransEntity> getTargetByPipelineId(Long id) {
		// TODO Auto-generated method stub
		return this.transEntityDao.getTargetByPipelineId(id);
	}
	
}
