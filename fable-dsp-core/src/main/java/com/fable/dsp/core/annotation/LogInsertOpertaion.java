package com.fable.dsp.core.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInsertOpertaion {

    @Autowired
    LogAnnotationDao logAnnotationDao;
    
    private List<Map> objList = new ArrayList<Map>();
    private List<String> detaillList = new ArrayList<String>();
    
    //标注该方法体为后置通知，当目标方法执行成功后执行该方法体  
//    @AfterReturning("within(com.fable.dsp..*) && @annotation(loginsertannotation)") 
    @AfterReturning("within(com.fable.dsp..*) && @annotation(logAnnotation)")
    public void addLogSuccess(JoinPoint jp, LogAnnotation logAnnotation)  throws Throwable{
        //清空以前的记录
        objList.clear();
        detaillList.clear();
        Object[] params = jp.getArgs();//获取目标方法体参数  
        for (Object param : params) {
            objList.add(BeanUtils.describe(param));
        }
        //获取id
        String[] id = getId(logAnnotation.keyIndex(),logAnnotation.primaryKey());
        //获取操作人id
        String createUser = (String)objList.get(logAnnotation.userIndex()-1).get("createUser");
        
        for (int i=0;i<id.length;i++) {
            UserLogEntity log = new UserLogEntity();
            log.setOperationTime(new Date());
            log.setOperationUser(createUser);
            log.setTargetId(id[i]);
            log.setOperationType(logAnnotation.operationType()[i]+"");
            log.setOperationDescribe(logAnnotation.operationDescribe()[i]);
            log.setTargetDetaill(detaillList.get(i));
            log.setTargetName(logAnnotation.targetName()[i]);
            logAnnotationDao.insert(log);
        }
    }
    
    /**
     * 获取该操作方法产生影响的记录的id.
     * @param keyIndex index可以从第几个参数中获得到主键 
     * @param primaryKey 主键的属性名 如 ：id 、taskId 等
     * 
     */
    public String[] getId(int[] keyIndex,String[] primaryKey) {
        String [] id = new String[keyIndex.length];
        for(int i = 0;i<keyIndex.length;i++) {
            detaillList.add(objList.get(keyIndex[i]-1).toString());
            id[i] = (String)objList.get(keyIndex[i]-1).get(primaryKey[i]);
        }
        return id;
    }
    
}
