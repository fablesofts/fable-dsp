package com.fable.dsp.service.dataswitch.impl;

import java.io.IOException;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.dto.dataswitch.DataSourceDto;
import com.fable.dsp.common.dto.dataswitch.TransEntityJsonDto;
import com.fable.dsp.common.util.DataBaseTypes;
import com.fable.dsp.common.util.EncryptDES;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.dmo.dataswitch.TransEntity;


public class TaskTransUtil {
    public static TransEntity transfer(){
        return null;
    }
    /**
     * 将DataSource的dmo转换成dto
     * @param sourceInfo datasource的dmo
     * @return datasource的dto
     * @throws IOException IO异常
     * @throws Exception
     */
    public static DataSourceDto transferDataSource(DataSourceInfo sourceInfo) throws IOException, Exception {
        DataSourceDto dataSourceDto=new DataSourceDto();
        dataSourceDto.setPassword(
            EncryptDES.decrypt(sourceInfo.getPassword(),
                CommonConstants.DES_KEY)); 
        dataSourceDto.setConnect_url(sourceInfo.getConnect_url());
        dataSourceDto.setDb_name(sourceInfo.getDb_name());
        if (CommonConstants.ORACLE_TYPE.equals(sourceInfo.getDb_type()
            .trim()))
            dataSourceDto.setDb_type(DataBaseTypes.ORACLE);
        else if (CommonConstants.MYSQL_TYPE.equals(sourceInfo.getDb_type()
            .trim()))
            dataSourceDto.setDb_type(DataBaseTypes.MYSQL);
        else if (CommonConstants.SQLSERVER_TYPE.equals(sourceInfo
            .getDb_type().trim()))
            dataSourceDto.setDb_type(DataBaseTypes.SQLSERVER);
        else if (CommonConstants.DM_TYPE.equals(sourceInfo.getDb_type()
            .trim()))
            dataSourceDto.setDb_type(DataBaseTypes.DM);
        else if(CommonConstants.DM_TYPE_7.equals(sourceInfo.getDb_type()
        		.trim())) {
        	dataSourceDto.setDb_type(DataBaseTypes.DM7);
        }else{
        	dataSourceDto.setDb_type(DataBaseTypes.OTHER);
        }
        dataSourceDto.setDel_flag(sourceInfo.getDel_flag());
        dataSourceDto.setName(sourceInfo.getName());
        dataSourceDto.setPort(sourceInfo.getPort());
        dataSourceDto.setServer_ip(sourceInfo.getServer_ip());
        dataSourceDto.setSource_type(sourceInfo.getSource_type());
        dataSourceDto.setUsername(sourceInfo.getUsername());
        dataSourceDto.setRoot_path(sourceInfo.getRoot_path());
        return dataSourceDto;
    }
    /**
     * 将实体的dmo转换成dto
     * @param entityJsonDto
     * @return
     */
    public static TransEntity
        transferEntity(TransEntityJsonDto entityJsonDto) {
        return null;
    }

}
