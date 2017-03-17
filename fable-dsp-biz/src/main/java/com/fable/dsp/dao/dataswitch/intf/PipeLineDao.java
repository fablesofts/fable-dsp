package com.fable.dsp.dao.dataswitch.intf;

import java.math.BigDecimal;
import java.util.List;

import com.fable.dsp.common.dto.dataswitch.AddTableDto;
import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TransEntity;
/**
 * 操作子任务的DAO
 * @author Administrator
 *
 */
public interface PipeLineDao extends GenericDao<PipeLine>{
	/**
	 * 通过条件查找多条
	 * @param page 分页
	 * @param pipeLine 条件
	 * @return
	 */
	List<PipeLine>listPipeLine(Page page,PipeLine pipeLine);
	/**
	 * 通过条件查询1个
	 * @param pipeLine
	 * @return
	 */
	PipeLine getPipeLineByConditions(PipeLine pipeLine);
	/**
	 * 通过条件分页查询
	 * @param page
	 * @param pipeLine
	 * @return
	 */
	PageList<PipeLine>findPipeListList(Page page,PipeLine pipeLine);
	/**
	 * 计算总记录数
	 * @param page
	 * @param pipeLine
	 * @return
	 */
	public Long listCount(Page page,final PipeLine pipeLine);
	List<PipeLine> listPipelinesById(Long id);
	/**
	 * 根据pipeline id查找实体
	 * @param id
	 * @param flg 1:source 0:target
	 * @return
	 */
	TransEntity getSourceByPId(final Long id);
	TransEntity getTargetById(final Long id);
	/**
	 * 根据taskid查找相应的pipeline
	 * @param id   taskid
	 * @return 子任务
	 */
    List<PipeLine> findPipeLineByTaskId(Long id);
    List<BigDecimal> getpipidsByTaskId(Long taskId);
   
    /**
     * 根据任务编号和子任务编号查询任务类型
     * @param taskId    任务编号
     * @param long1 子任务编号
     * @return
     */
    List<Character> getEntityByPid(Long taskId, Long long1);
    /**
     * 创建增量表
     * @param taskId
     * @return
     * @throws Exception
     */
    AddTableDto getAddTables(Long taskId) throws Exception;
    
}
