package org.fable.dsp.biz.example;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fable.dsp.dmo.dataswitch.PipeLine;
import com.fable.dsp.dmo.dataswitch.TransEntity;
import com.fable.dsp.service.dataswitch.intf.PipeLineInfoService;
import com.fable.dsp.service.dataswitch.intf.TransEntityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext-test.xml"})
public class EditTaskTest {
	@Resource
	PipeLineInfoService pipeLineService;
	@Resource
	TransEntityService transEntityService; 
	@Test
	public void test(){
//		List<PipeLine>pipelines=pipeLineService.listPipelinesById(500L);
//		System.out.println(pipelines.size());
		//List<TransEntity>entities=transEntityService.getTargetByPipelineId(1050L);
		//System.out.println(entities.get(0).getName());
	}
}
