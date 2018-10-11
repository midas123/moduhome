package com.kh.collecting;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CollectingController {
	
	@Resource(name="collectingService")
	private CollectingService collectingService;
	
	@RequestMapping(value="/collectingReg", method = RequestMethod.POST)
	public @ResponseBody int regCollecting(@RequestBody CollectingModel collectingModel) throws Exception{

		System.out.println("collectingReg가 실행?");
		System.out.println(collectingModel.getMg_number());
		System.out.println(collectingModel.getMember_number());

		int exist = collectingService.collectingExist(collectingModel.getMg_number(), collectingModel.getMember_number());
		//mv.addObject("exist", exist);
		
		System.out.println("exist의 값은 : " + exist);
		if(exist == 0) {
			collectingService.collectingReg(collectingModel.getMg_number(), collectingModel.getMember_number());
			return 1;
		}
		else {
			collectingService.collectingDel(collectingModel.getMg_number(), collectingModel.getMember_number());
			return 0;
		}
	}
}
