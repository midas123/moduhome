package com.kh.moduhome;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.kh.goods.GoodsService;
import com.kh.mg.MgService;
import com.kh.moduhome.CommandMap;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Resource(name = "mgService")
	private MgService mgService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	// 홈
	@RequestMapping(value = "/")
	public ModelAndView home(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		List<Map<String, Object>> mgList = mgService.mgList(commandMap.getMap());
		List<Map<String, Object>> newItem = goodsService.newItemAll(commandMap.getMap());
		List<Map<String, Object>> glList = mgService.glList(commandMap.getMap());
		
		
		
		mv.addObject("glList", glList);
		mv.addObject("mgList", mgList);
		mv.addObject("newItem", newItem);
		mv.setViewName("home");
		
		return mv;

	}
	
	/*// 홈
	@RequestMapping(value = "/main")
	public ModelAndView main(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/main");
		

		
		return mv;
	}*/
	
	@RequestMapping(value = "/my2")
	public ModelAndView main(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("my2");
		

		
		return mv;
	}

}
