package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;

/**
 * Crontab表达式Dto
 * @author Administrator
 *
 */
public class CrontabDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 141663591235385220L;
	private String beginDate;//?年?月?日
    private String runModel;    //运行模式：周期？定期
    private int hourDay;
    private int minuteDay;
    private int dayWeek; //一周中的几日
    private int hourWeek;
    private int minuteWeek;
    private int dayMonth;//一月中的几日
    private int hourMonth;
    private int minuteMonth;
    private String circleModel; //周期模式
    private int circleTime;//每小时？每分钟？每秒
    private String taskId;
    private String scheduleId;//用于调度信息的修改
    private String model;   //按天？按周？按月
    private int bySecond;//按秒
    private int byMinute;   //按分
    private int byHour; //按时
    private boolean executeimmediately;//是否立刻执行一次
    
    
    
    
    public boolean isExecuteimmediately() {
		return executeimmediately;
	}





	public void setExecuteimmediately(boolean executeimmediately) {
		this.executeimmediately = executeimmediately;
	}





	public String getScheduleId() {
		return scheduleId;
	}





	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}





	public int getBySecond() {
        return bySecond;
    }




    
    public void setBySecond(int bySecond) {
        this.bySecond = bySecond;
    }




    
    public int getByMinute() {
        return byMinute;
    }




    
    public void setByMinute(int byMinute) {
        this.byMinute = byMinute;
    }




    
    public int getByHour() {
        return byHour;
    }




    
    public void setByHour(int byHour) {
        this.byHour = byHour;
    }




    public String getCircleModel() {
        return circleModel;
    }



    
    public void setCircleModel(String circleModel) {
        this.circleModel = circleModel;
    }



    
    public int getCircleTime() {
        return circleTime;
    }



    
    public void setCircleTime(int circleTime) {
        this.circleTime = circleTime;
    }



    public String getBeginDate() {
        return beginDate;
    }


    
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }


    
   


    public String getRunModel() {
        return runModel;
    }

    
    public void setRunModel(String runModel) {
        this.runModel = runModel;
    }

    public int getHourDay() {
        return hourDay;
    }
    
    public void setHourDay(int hourDay) {
        this.hourDay = hourDay;
    }
    
    public int getMinuteDay() {
        return minuteDay;
    }
    
    public void setMinuteDay(int minuteDay) {
        this.minuteDay = minuteDay;
    }
    
    
    
    
    public int getDayWeek() {
        return dayWeek;
    }





    
    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }





    public int getHourWeek() {
        return hourWeek;
    }
    
    public void setHourWeek(int hourWeek) {
        this.hourWeek = hourWeek;
    }
    
    public int getMinuteWeek() {
        return minuteWeek;
    }
    
    public void setMinuteWeek(int minuteWeek) {
        this.minuteWeek = minuteWeek;
    }
    
   
    
    public int getDayMonth() {
        return dayMonth;
    }

    
    public void setDayMonth(int dayMonth) {
        this.dayMonth = dayMonth;
    }

    public int getHourMonth() {
        return hourMonth;
    }
    
    public void setHourMonth(int hourMonth) {
        this.hourMonth = hourMonth;
    }
    
    public int getMinuteMonth() {
        return minuteMonth;
    }
    
    public void setMinuteMonth(int minuteMonth) {
        this.minuteMonth = minuteMonth;
    }
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
   
}
