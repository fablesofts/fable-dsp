package com.fable.dsp.dao.dataswitch.intf;

import com.fable.dsp.core.dao.GenericDao;
import com.fable.dsp.dmo.dataswitch.EtlStrategy;
import com.fable.dsp.dmo.schedule.Schedule;

public interface StrategyDao extends GenericDao<EtlStrategy>{
	
    /**
     * 更具pipeLine id 删除dsp_etl_strategy
     * @param id
     * @return
     */
    Boolean deleteEtlByPipeLineId(Long id);
}
