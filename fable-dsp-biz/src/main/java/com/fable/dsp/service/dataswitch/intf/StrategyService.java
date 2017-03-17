package com.fable.dsp.service.dataswitch.intf;

import com.fable.dsp.core.service.GenericService;
import com.fable.dsp.dmo.dataswitch.EtlStrategy;


public interface StrategyService extends GenericService<EtlStrategy>{

    Boolean deleteEtlByPipeLineId(Long id);
}
