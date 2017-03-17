package com.fable.dsp.controller.datasource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.dsp.core.datagrid.DataGridModel;
import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.datasource.DataSourceInfo;
import com.fable.dsp.service.datasource.intf.DataSourceInfoService;

/**
 * 数据源信息
 * 
 * @author 吴浩
 */
@Controller
@RequestMapping("/dataSource")
public class DataSourceInfoController {

    @Autowired
    DataSourceInfoService dataSourceService;

    @RequestMapping("/maintain")
    public String maintain() {
        return "data_source/source-manager";
    }

    /**
     * 分页查询(DSP_DATA_SOURCE)
     * 
     * @param dgm
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("listDataSource")
    @ResponseBody
    public Map<String, Object> listDataSource(DataGridModel dgm)
        throws Exception {
        Page pager = new Page();

        // 当前页,对应分页中的pageNumber参数;
        int currentPage = (dgm.getPage() == 0) ? 1 : dgm.getPage();
        // 每页显示条数,对应EASYUI分页中的pageSize参数;
        int pageSize = dgm.getRows() == 0 ? 10 : dgm.getRows();
        // 每页的开始记录 第一页为1 第二页为number +1
        int index = (currentPage - 1) * pageSize;
        // 设置分页查询参数
        pager.setCurrentPage(currentPage);
        pager.setPageSize(pageSize);
        pager.setIndex(index);
        // 查询分页结果
        DataSourceInfo ds = new DataSourceInfo();
        // ds.setDel_flag(Long.parseLong("0"));
        PageList<DataSourceInfo> pageSource =
            dataSourceService.findPageDataSourceInfo(pager, ds);


        /**
         * json数据容器
         */
        Map<String, Object> rootJson = new HashMap<String, Object>();

        rootJson.put("rows", pageSource.getList());
        rootJson.put("total", pageSource.getPage().getCount());

        return rootJson;
    }

    /**
     * 保存数据(Insert)
     * 
     * @param source
     * @param session
     * @return
     */
    @RequestMapping("saveDataSource")
    @ResponseBody
    public Map<String, Object> saveDataSource(DataSourceInfo source,
        HttpSession session) {
        /**
         * json数据容器
         */
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            DataSourceInfo dTemp = new DataSourceInfo();
            dTemp.setName(source.getName());

            String user = (String)session.getAttribute("username");
            source.setCreateUser(user);
            source.setCreateTime(new Date());
            dataSourceService.insert(source);
            rootJson.put("dealFlag", true);
            rootJson.put("msg", "保存成功");
        }
        catch (Exception e) {
            rootJson.put("dealFlag", false);
            rootJson.put("msg", "保存失败");
        }
        return rootJson;
    }

    /**
     * 修改数据(Update)
     * 
     * @param source
     * @param session
     * @return
     */
    @RequestMapping("updateDataSource")
    @ResponseBody
    public Map<String, Object> updateDataSource(DataSourceInfo source,
        HttpSession session) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            String user = (String)session.getAttribute("username");
            source.setUpdateUser(user);
            source.setUpdateTime(new Date());
            //判断数据源类型
            if("0".equals(source.getSource_type())){
            	//如果是数据库，就把root_path（文件路径）设置为null
            	source.setRoot_path(null);
            }else{
            	//如果是文件，就把db_type、db_name和connect_url设置为null
            	source.setDb_type(null);
            	source.setDb_name(null);
            	source.setConnect_url(null);
            }
            //更新数据源数据
            dataSourceService.update(source);
            rootJson.put("dealFlag", true);
            rootJson.put("msg", "修改成功");

        }
        catch (Exception e) {
            rootJson.put("dealFlag", false);
            rootJson.put("msg", "修改失败");
        }
        return rootJson;
    }

    /**
     * 根据ID删除数据(Delete)
     * 
     * @param source
     * @param session
     * @return
     */
    @RequestMapping("deleteDataSource")
    @ResponseBody
    public Map<String, Object> deleteDataSource(DataSourceInfo source,
        HttpSession session) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
//            String user = (String)session.getAttribute("username");
//            source.setCreateUser(user);
//            source.setCreateTime(new Date());
//            dataSourceService.deleteById(source.getId());
        	
            //判断该数据源是否已经配置了任务，如果有任务则不能删除，否则可以删除
            String deleteMess = dataSourceService.isDelete(source.getId());
            rootJson.put("dealFlag", true);
            if(deleteMess == null || "".equals(deleteMess)){
                rootJson.put("msg", "删除成功");
            }else{
                rootJson.put("msg", deleteMess);
            }
        }
        catch (Exception e) {
            rootJson.put("dealFlag", false);
            rootJson.put("msg", "删除失败");
        }
        return rootJson;
    }

    /**
     * 根据ID删除数据(Delete)
     * 
     * @param source
     * @param session
     * @return
     */
    @RequestMapping("deleteFlagDataSource")
    @ResponseBody
    public Map<String, Object> deleteFlagDataSource(DataSourceInfo source,
        HttpSession session) {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        try {
            String user = (String)session.getAttribute("username");
            DataSourceInfo source2 =
                dataSourceService.getById(source.getId());
            source2.setCreateUser(user);
            source2.setCreateTime(new Date());
            source2.setDel_flag(Long.parseLong("1"));
            dataSourceService.update(source2);
            rootJson.put("dealFlag", true);
            rootJson.put("msg", "删除成功");
        }
        catch (Exception e) {
            rootJson.put("dealFlag", false);
            rootJson.put("msg", "删除失败");
        }
        return rootJson;
    }

    /**
     * 根据ID查询数据(Select)
     * 
     * @param source
     * @return
     * @throws Exception
     */
    @RequestMapping("getDataSourceInfo")
    @ResponseBody
    public DataSourceInfo getDataSourceInfo(DataSourceInfo source)
        throws Exception {
        source = dataSourceService.getById(source.getId());
        return source;
    }

    /**
     * 判断设备的网段
     * 
     * @param source
     * @return
     * @throws Exception
     */
    @RequestMapping("getDeviceType")
    @ResponseBody
    public Map<String, Object> getDeviceType(DataSourceInfo source)
        throws Exception {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        if (dataSourceService.getDeviceType(source)) {
            System.out.println("***********进来啦true*************");
            rootJson.put("dealFlag", true);
            rootJson.put("deviceType", "i");
        }
        else {
            System.out.println("***********进来啦false*************");
            rootJson.put("dealFlag", true);
            rootJson.put("deviceType", "o");
        }
        return rootJson;
    }

    // testNetwork
    /**
     * 测试网络(Select)
     * 
     * @param source
     * @return
     * @throws Exception
     */
    @RequestMapping("testNetwork")
    @ResponseBody
    public Map<String, Object> testNetwork(DataSourceInfo source,String datatype)
        throws Exception {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        String mess = dataSourceService.testNetwork(source,datatype);
        rootJson.put("dealFlag", true);
        rootJson.put("isConn", mess);
        return rootJson;
    }

    /**
     * 判断同名文件
     * 
     * @param source
     * @return
     * @throws Exception
     */
    @RequestMapping("getSameSourceName")
    @ResponseBody
    public Map<String, Object> getSameSourceName(DataSourceInfo source)
        throws Exception {
        Map<String, Object> rootJson = new HashMap<String, Object>();
        boolean isSame = dataSourceService.getSameSourceName(source);
        rootJson.put("dealFlag", isSame);
        return rootJson;
         
    }

}
