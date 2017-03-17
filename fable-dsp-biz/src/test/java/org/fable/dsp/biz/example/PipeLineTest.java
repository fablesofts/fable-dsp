package org.fable.dsp.biz.example;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fable.dsp.core.page.Page;
import com.fable.dsp.core.page.PageList;
import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TransEntity;
import com.fable.dsp.service.dataswitch.intf.PipeLineInfoService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
public class PipeLineTest {
	@Resource
	PipeLineInfoService pipeLineService; 
	@Test
	public void test2(){
		/**
		Page page=new Page();
		//在 service层进行测试
		//pipeLineInfoService.insert(new PipeLine());
		PageList<PipeLine>pages=pipeLineInfoService.listPipeLineListByConditions(page,new PipeLine());
		System.out.println("总记录数是"+pages.getList().size());
		**/
		//某一个pipeline
		//1个源
		try {
			System.out.println(11);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
//		TransEntity source=pipeLineService.getSourceByPipeId(2600L);
//		//多个源
//		TransEntity target=pipeLineService.getTargetByPipeId(2600L);
	}
}
