package com.fable.dsp.service.dataswitch.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.dataswitch.intf.PipeLineDao;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TransEntity;
import com.fable.dsp.service.dataswitch.intf.PipeLineInfoService;
@Service("pipeLineService")
public class PipeLineServiceImpl implements PipeLineInfoService{
	@Autowired
	PipeLineDao pipeLineDao;
	
	public void insert(PipeLine entity) {
		// TODO Auto-generated method stub
		pipeLineDao.insert(entity);
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		pipeLineDao.deleteById(id);
	}

	
	public void delete(PipeLine entity) {
		// TODO Auto-generated method stub
		pipeLineDao.delete(entity);
	}

	
	public void update(PipeLine entity) {
		// TODO Auto-generated method stub
		pipeLineDao.update(entity);
	}

	
	public PipeLine getById(Long id) {
		// TODO Auto-generated method stub
		return pipeLineDao.getById(id);
	}
	//没有分页条件的
	
	public PageList<PipeLine> listPipeLineListByConditions(Page page,
			PipeLine pipeLine) {
		//在业务层进行处理
		PageList<PipeLine>pageList=new PageList<PipeLine>();
		//得到list
		pageList.setList(pipeLineDao.listPipeLine(page, pipeLine));
		//得到总记录数
		page.setCount(pipeLineDao.listCount(page, pipeLine));
		pageList.setPage(page);
		return pageList;
	}

	
	public PipeLine getPipeLineByConditions(PipeLine pipeLine) {
		// TODO Auto-generated method stub
		return pipeLineDao.getPipeLineByConditions(pipeLine);
	}

	
	public PageList<PipeLine> findPagePipeLineInfo(Page page, PipeLine pipeLine) {
		// TODO Auto-generated method stub
		return pipeLineDao.findPipeListList(page, pipeLine);
	}

	
	public List<PipeLine> listPipelinesById(Long id) {
		return pipeLineDao.listPipelinesById(id);
	}


	@Override
	public TransEntity getTargetByPipeId(Long id) {
		// TODO Auto-generated method stub
		return pipeLineDao.getTargetById(id);
	}


	@Override
	public TransEntity getSourceByPipeId(Long id) {
		// TODO Auto-generated method stub
		return pipeLineDao.getSourceByPId(id);
	}


    @Override
    public List<PipeLine> findPipeLineByTaskId(Long taskId) {
        // TODO Auto-generated method stub
        return pipeLineDao.findPipeLineByTaskId(taskId);
    }


    @Override
    public List<Long> getpipidsByTaskId(Long taskId) {
        // TODO Auto-generated method stub
        return null;
    }


	
	
	
}
