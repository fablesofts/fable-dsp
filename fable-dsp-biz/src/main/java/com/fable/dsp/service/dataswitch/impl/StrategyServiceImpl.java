package com.fable.dsp.service.dataswitch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fable.dsp.dao.dataswitch.intf.ScheduleDao;
import com.fable.dsp.dao.dataswitch.intf.StrategyDao;
import com.fable.dsp.dmo.dataswitch.EtlStrategy;
import com.fable.dsp.service.dataswitch.intf.StrategyService;

@Service("strategyService")
@Transactional(propagation = Propagation.REQUIRED,
    rollbackFor = Exception.class)
public class StrategyServiceImpl implements StrategyService {
    
    @Autowired
    private StrategyDao strategydao;
    
    @Override
    public void insert(EtlStrategy entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(EtlStrategy entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(EtlStrategy entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public EtlStrategy getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteEtlByPipeLineId(Long id) {
        return strategydao.deleteEtlByPipeLineId(id);
    }

}
