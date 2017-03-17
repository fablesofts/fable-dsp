package com.fable.dsp.controller.proxy;


import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.hamal.proxy.intf.OuterRmiAddress;
import com.fable.hamal.shuttle.common.utils.rmi.RmiUtil;

public class OuterRmiAddressImpl implements OuterRmiAddress {

    @Override
    public String getOutRmiAddress() {

        return SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS);
    }

}
