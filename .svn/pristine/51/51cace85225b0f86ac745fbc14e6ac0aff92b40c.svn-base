package com.fable.dsp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fable.dsp.common.dto.dataswitch.CrontabDto;
import com.fable.dsp.common.exception.TaskException;
/**
 * 验证crontab表达式的工具类
 * @author 邱爽
 *
 */
public class CrontabUtil {
    /**
     * 判断一个字符串当中是否存在数字
     * @param str
     * @return
     */
    public static boolean hasDigit(String content){
        boolean flag=false;
       Pattern p=Pattern.compile(".*\\d+.*");
       Matcher m=p.matcher(content);
       if(m.matches())
           flag=true;
        return flag;
    }
    /**
     * 判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){   
         if (!Character.isDigit(str.charAt(i))){
          return false;
         }
        }
        return true;
       }
    /**
     * 根据crontab表达式封装crontabDto对象
     * @param crontabDto crontabDto对象
     * @param crontabExpression crontab表达式
     */
    public static void  transToCrontabDto(CrontabDto  crontabDto,String crontabExpression){
    	//判断是否包含“$$”符号,包含的就是定期模式，否则是周期模式
        if(crontabExpression.indexOf("$$")>0){
            //周期模式：始于几点：每小时每分钟
            crontabDto.setRunModel("circle-execute");
            String  beginDate=crontabExpression.substring(0,crontabExpression.indexOf('$'));
            crontabDto.setBeginDate(beginDate);
            //完整的crontab表达式
            String expression=crontabExpression.substring(crontabExpression.lastIndexOf('$')+1,crontabExpression.length());
            String[]str=expression.split(" ");
            //* * */15 * ? *
            if(CrontabUtil.hasDigit(str[2])){
                //按小时
                int hour=Integer.parseInt(str[2].substring(2,str[2].length()));
                crontabDto.setByHour(hour);
                crontabDto.setCircleModel("byHour");
            }else if(CrontabUtil.hasDigit(str[1])){
                int minute=Integer.parseInt(str[1].substring(2,str[1].length()));
                crontabDto.setByMinute(minute);
                crontabDto.setCircleModel("byMinute");
            }else{
                int second=Integer.parseInt(str[0].substring(2,str[0].length()));
                crontabDto.setBySecond(second);
                crontabDto.setCircleModel("bySecond");
            }
        }else{
            //定期模式：每天几点几点
            String []str=crontabExpression.split(" ");
            crontabDto.setRunModel("regular-execute");
            //按月，第6位是数字  
            //0 1 3 12 * ?   每月12日3时1分
            //
            if(CrontabUtil.isNumeric(str[3])){
                crontabDto.setDayMonth(Integer.parseInt(str[3]));
                crontabDto.setHourMonth(Integer.parseInt(str[2]));
                crontabDto.setMinuteMonth(Integer.parseInt(str[1]));
                crontabDto.setModel("bymonth");
            }else if(CrontabUtil.isNumeric(str[5])) {
                //按周
                crontabDto.setDayWeek(Integer.parseInt(str[5]));
                crontabDto.setHourWeek(Integer.parseInt(str[2]));
                crontabDto.setMinuteWeek(Integer.parseInt(str[1]));
                crontabDto.setModel("byweek");
            }else{
             // 按日：第4位之后没有数字  每天8点4分
                //0 4 8 * * ?  
                crontabDto.setHourDay(Integer.parseInt(str[2]));
                crontabDto.setMinuteDay(Integer.parseInt(str[1]));
                crontabDto.setModel("byday");
            }
        }
    }
    /**
     * 将crontabdto转换成crontab表达式
     * @param crontabDto crontabdto对象
     * @return crontab表达式
     */
    public static String transFromCrontabDto(CrontabDto  crontabDto) {
    	 String crontabExpression=null;
        if ("regular-execute".equals(crontabDto.getRunModel())) {
            if ("bymonth".equals(crontabDto.getModel())) {
                //"0 15 10 15 * ?" 每月15日上午10:15触发 
                try {
                    crontabExpression =
                        String.format("0 %d %d %d * ?",
                            crontabDto.getMinuteMonth(),
                            crontabDto.getHourMonth(),
                            crontabDto.getDayMonth());
                }
                catch (Exception e) {
                    throw new TaskException();
                }
            }
            else if ("byweek".equals(crontabDto.getModel())) {
                //"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
                crontabExpression =
                    String.format("0 %d %d ? * %d",
                        crontabDto.getMinuteWeek(),
                        crontabDto.getHourWeek(), crontabDto.getDayWeek());
            }
            else {
                crontabExpression =
                    String.format("0 %d %d * * ?",
                        crontabDto.getMinuteDay(), crontabDto.getHourDay());
            }
            return crontabExpression;
        }else{
            //周期模式
            //2013-10-01| 12:30:40 $$ * * * * ? *
            StringBuilder sbcrontab=new StringBuilder();
            sbcrontab.append(crontabDto.getBeginDate()+"$$");
            String strCircle="";
           
            if("byHour".equals(crontabDto.getCircleModel())){
                strCircle= String.format("* * */%d * ? *",
                    crontabDto.getByHour());
            }else if("byMinute".equals(crontabDto.getCircleModel())){
                strCircle= String.format("* */%d * * * ? *",
                    crontabDto.getByMinute());
            }else{
                strCircle= String.format("*/%d * * * * ? *",
                    crontabDto.getBySecond());
            }
            sbcrontab.append(strCircle);
            return sbcrontab.toString();
            
        }
    }
}
