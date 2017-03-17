package org.fable.dsp.biz.example;

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
public class EntityTest {
	@Resource
	TransEntityService transEntityService;
	@Resource
	PipeLineInfoService pipeLineService;
	@Test 
	public void testAdd(){
		//插入两实体
		//transEntityService.insert(new TransEntity("entity1"));
		//transEntityService.insert(new TransEntity("entity2"));
		Long id1=transEntityService.getIdByName("entity1");
		Long id2=transEntityService.getIdByName("entity2");
		PipeLine pipeLine=new PipeLine(new TransEntity(id1),new TransEntity(id2));
		pipeLineService.insert(pipeLine);
	}
}
