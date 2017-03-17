package org.fable.dsp.biz.example;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fable.dsp.dao.dataswitch.intf.PipeLineDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
public class ExampleTest {

	@Resource
	PipeLineDao pipeLineDao;
	@Test
	public void test() {
	    try {
	        //List<String>list=pipeLineDao.getEntityByPid(7200L,7200L);
	       // System.out.println(list);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
