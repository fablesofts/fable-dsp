package org.fable.dsp.biz.example;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fable.dsp.dmo.dataswitch.TaskEntity;
import com.fable.dsp.service.dataswitch.intf.TaskService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
public class TaskTest {
	@Resource
	private TaskService taskService;
	@Test
	public void test1(){
		/**分页bean
		Page page=new Page();
		page.setCurrentPage(1);
		page.setPageSize(2);
		page.setIndex(1);
		//在service层进行测试
		PageList<TaskEntity>pages=taskService.listTaskByConditions(page, taskEntity);
		System.out.println("总记录数1："+pages.getList().size());
		System.out.println("总记录数2："+pages.getPage().getCount());
		**/
//		TaskEntity taskEntity=taskService.getById(4450L);
//		System.out.println("名称"+taskEntity.getName());
	    boolean isExist=taskService.isExitsName("打算的撒");
	    System.out.println(isExist);
	}
}
