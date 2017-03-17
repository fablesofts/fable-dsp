package com.fable.dsp.controller.dataswitch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dataswitch")
public class PageController {
	@RequestMapping("/tab1-maintain")
    public String maintain() {
        return "dataswitch/tab1";
    }
	@RequestMapping("/href/tab1")
    @ResponseBody
    public String gototab1(){
		return "dataswitch/tab1";
	}
}
