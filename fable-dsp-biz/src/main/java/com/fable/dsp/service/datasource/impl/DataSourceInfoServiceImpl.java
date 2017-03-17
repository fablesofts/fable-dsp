package com.fable.dsp.service.datasource.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.dsp.common.constants.CommonConstants;
import com.fable.dsp.common.constants.SysConfigConstants;
import com.fable.dsp.common.util.EncryptDES;
import com.fable.dsp.common.util.NetworkCard;
import com.fable.dsp.common.util.SysConfigUtil;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dao.datasource.intf.DataSourceInfoDao;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.service.datasource.intf.DataSourceInfoService;
import com.fable.hamal.shuttle.common.utils.rmi.RmiUtil;
import com.fable.hamal.shuttle.communication.client.Communication;
import com.fable.outer.rmi.event.connectouter.JudgdbconnectEvent;
import com.fable.outer.rmi.event.connectouter.JudgnetworklinkEvent;
import com.fable.outer.rmi.event.dto.DataSourceDto;
import com.fable.outer.rmi.type.DataBaseTypes;

@Service("dataSourceInfoService")
public class DataSourceInfoServiceImpl implements DataSourceInfoService {

    @Autowired
    DataSourceInfoDao dataSource;

    @Resource(name = "client")
    Communication communication;

//    private static String OUTER_RMI_ADDRESSES = "outer.rmi.addresses";



    String password = "";

    /**
     * 根据条件，查询数据
     */
    @Override
    public List<DataSourceInfo> listDataSourceInfoListByConditions(
        final DataSourceInfo source) {
        // TODO Auto-generated method stub
        return this.dataSource.listDataSourceInfoListByConditions(source);
    }

    /**
     * 删除数据
     */
    @Override
    public void delete(final DataSourceInfo source) {
        // TODO Auto-generated method stub
        this.dataSource.delete(source);
    }

    /**
     * 根据ID删除数据
     */
    @Override
    public void deleteById(final Long id) {
        this.dataSource.deleteById(id);
    }

    /**
     * 根据ID查询数据(展现密码默认字符)
     */
    @Override
    public DataSourceInfo getById(final Long id) {

        return this.dataSource.getById(id);
    }

    /**
     * 根据ID查询数据
     */
    @Override
    public DataSourceInfo getByOne(final Long id) {

        return this.dataSource.getById(id);
    }

    /**
     * 不解密获取DataSource
     */
    @Override
    public DataSourceInfo getNoEncryptById(final Long id) {
        DataSourceInfo ds = null;
        try {
            ds = this.dataSource.getById(id);
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    /**
     * 添加数据
     */
    @Override
    public void insert(final DataSourceInfo source) {
        // TODO Auto-generated method stub

        try {
            // 密码加密处理入库
            this.password =
                EncryptDES.encrypt(source.getPassword(),
                    CommonConstants.DES_KEY);
            source.setPassword(this.password);
            if (CommonConstants.FILE_TYPE
                .equals(source.getSource_type().trim())) {
                source.setDb_type(CommonConstants.FTP_TYPE);
                source.setRoot_path("/");
            }
            this.dataSource.insert(source);
        }
        catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * 修改数据
     */
    @Override
    public void update(final DataSourceInfo source) {
        // TODO Auto-generated method stub
        try {
            if (!CommonConstants.DS_PASSWORD.equals(source.getPassword())) {
                // 密码加密处理入库
                System.out.println("**不等于默认密码，加密处理**");
                this.password =
                    EncryptDES.encrypt(source.getPassword(),
                        CommonConstants.DES_KEY);
                source.setPassword(this.password);
            }
            else {
                // 如果没有修改过密码，就从插入回原先存数据库的密码
                System.out.println("**等于默认密码，查询数据库处理**");
                DataSourceInfo dsi = this.getByOne(source.getId());
                // 将dsi实体对象改成游离状态
                dataSource.getOldDataSourceInfo(dsi);
                this.password = dsi.getPassword();
                source.setPassword(this.password);

            }

            if (CommonConstants.FILE_TYPE
                .equals(source.getSource_type().trim())) {
                source.setDb_type(CommonConstants.FTP_TYPE);
                source.setRoot_path("/");
            }
            this.dataSource.update(source);
        }
        catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 分页查询整表数据(表名：DSP_DATA_SOURCE)
     */
    @Override
    public PageList<DataSourceInfo> findPageDataSourceInfo(final Page pager,
        final DataSourceInfo source) {
        // TODO Auto-generated method stub
        return this.dataSource.findPageDataSourceInfo(pager, source);
    }


    /**
     * 判断IP设备的类型（是内网IP还是外网IP）
     * 
     * @param DataSourceInfo
     *        source
     * @return boolean
     */
    @Override
    public boolean getDeviceType(final DataSourceInfo source) {
        final String innerCard = this.dataSource.getDrviceType();
        final Map netMap = NetworkCard.getNetworkCard();
        System.out.println(netMap.size() + "********count*********");
        final String nowIp = source.getServer_ip();
        String osIp = "";
        final Set<Map.Entry<String, String[]>> set = netMap.entrySet();
        for (final Entry<String, String[]> entry2 : set) {
            final Map.Entry<String, String[]> entry = entry2;
            System.out.println(entry.getKey());
            System.out.println(innerCard);
            if (innerCard.equals(entry.getKey()))
                osIp = entry.getValue()[0];
        }
        System.out.println(osIp + "****##***");
        if (osIp.substring(0, osIp.indexOf(".")).equals(
            nowIp.substring(0, nowIp.indexOf("."))))
            return true;
        else
            return false;
    }

    /**
     * 数据源网络测试
     * 
     * @param DataSourceInfo
     *        source
     * @return boolean
     */
    @Override
    public String testNetwork(final DataSourceInfo source, String datatype) {

        String mess = "";
        final DataSourceDto dsdto = new DataSourceDto();

        if ("update".equals(datatype.trim())) {
            if (!CommonConstants.DS_PASSWORD.equals(source.getPassword())) {
                // 密码加密处理入库
                System.out.println("**不等于默认密码，网络测试密码不坐任何处理**");
            }
            else {
                try {
                    // 如果没有修改过密码，就从插入回原先存数据库的密码
                    System.out.println("**等于默认密码，网络测试的密码查询数据库处理**");
                    DataSourceInfo dsi = this.getByOne(source.getId());
                    // 将dsi实体对象改成游离状态
                    dataSource.getOldDataSourceInfo(dsi);
                    this.password = dsi.getPassword();
                    this.password =
                        EncryptDES.decrypt(this.password,
                            CommonConstants.DES_KEY);
                    source.setPassword(this.password);
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        dsdto.setServer_ip(source.getServer_ip());
        dsdto.setPort(source.getPort());
        dsdto.setUsername(source.getUsername());
        dsdto.setPassword(source.getPassword());
        dsdto.setConnect_url(source.getConnect_url());
        
//        if (source.getDb_type() == null || "".equals(source.getDb_type()))
//            source.setDb_type("f");
        //判断数据源类型是文件或数据库
        if("0".equals(source.getSource_type())){
        	//如果数据源类型是数据库，再判断是那种数据库
        	System.out.println(source.getDb_type());
            if (CommonConstants.ORACLE_TYPE.equals(source.getDb_type().trim()))
                dsdto.setDb_type(DataBaseTypes.ORACLE);
            else if (CommonConstants.MYSQL_TYPE.equals(source.getDb_type().trim()))
                dsdto.setDb_type(DataBaseTypes.MYSQL);
            else if (CommonConstants.SQLSERVER_TYPE.equals(source.getDb_type()
                .trim()))
                dsdto.setDb_type(DataBaseTypes.SQLSERVER);
            else if (CommonConstants.DM_TYPE.equals(source.getDb_type().trim()))
                dsdto.setDb_type(DataBaseTypes.DM);
            else if(CommonConstants.DM_TYPE_7.equals(source.getDb_type().trim()))
                dsdto.setDb_type(DataBaseTypes.DM7);
            else
                dsdto.setDb_type(DataBaseTypes.OTHER);
        }else{
        	//数据源类型是文件
        	source.setDb_type("f");
        }
        
        if (!"f".equals(source.getDb_type().trim())) {
//            final boolean isConn =
//                (Boolean)this.communication.call(RmiUtil.getRmiUrl(DspPropertyConfigurer.getDspProperty(OUTER_RMI_ADDRESSES)),
//                    new JudgdbconnectEvent(dsdto));
        	//不再从配置文件中获取配置参数值
        	//从系统配置参数map中获取外服务rmi地址
            final boolean isConn =
                    (Boolean)this.communication.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)),
                        new JudgdbconnectEvent(dsdto));
            if (!isConn)
                mess =
                    (String)this.communication.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)),
                        new JudgnetworklinkEvent(dsdto));
            else
                mess = "数据源已连接成功！";
        }
        else {
            mess =
                (String)this.communication.call(RmiUtil.getRmiUrl(SysConfigUtil.getSysConfigValue(SysConfigConstants.OUTER_RMI_ADDRESS)),
                    new JudgnetworklinkEvent(dsdto));
        }

        return mess;

    }

    @Override
    public List<DataSourceInfo> listDataSourceInfoListByDb(
        final DataSourceInfo source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DataSourceInfo> listDataSourceInfoListByFile(
        final DataSourceInfo source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getSameSourceName(final DataSourceInfo source) {
        // TODO Auto-generated method stub
        return this.dataSource.getSameSourceName(source);
    }

    @Override
    public String isDelete(Long id) {
        String deleteMess = this.dataSource.isDelete(id);
        if(deleteMess == null){
            this.dataSource.deleteById(id);   
        }else{
            return deleteMess;
        }
        return deleteMess;
       
    }


}
