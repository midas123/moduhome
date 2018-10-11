package com.kh.adminMember;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mg.*;
import com.kh.moduhome.CommandMap;

@Controller
public class AdminMemberController {

	@Resource(name="adminMemberService")
	private AdminMemberService adminMemberService;
	
	@Resource(name="mgService")
	private MgService mgService;
	
	@RequestMapping(value = "/admin/memberList")
	public ModelAndView adminMemberList(HttpServletResponse response, HttpServletRequest request, CommandMap Map) throws Exception{
		ModelAndView mv = new ModelAndView("adminMemberList");
		
		List<Map<String, Object>> aml = adminMemberService.adminMemberList(Map.getMap());
		mv.addObject("adminMemberList", aml);
		
		List<Map<String, Object>> mgList = mgService.adminMgList(Map.getMap());
		mv.addObject("adminMgList", mgList);

		return mv;
		

	}
	
	
}
