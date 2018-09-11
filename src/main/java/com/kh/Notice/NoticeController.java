package com.kh.Notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.moduhome.CommandMap;


@Controller
public class NoticeController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	//����Ʈ ���� ��Ʈ�ѷ� �ڵ�
	@RequestMapping(value="/noticeList")
	public ModelAndView openNoticeList(CommandMap commandMap) throws Exception {
		ModelAndView mv= new ModelAndView("noticeList");
		
		List<Map<String,Object>> noticeList = noticeService.selectNoticeList(commandMap.getMap());
		mv.addObject("noticeList", noticeList);
		
		return mv;
	}
	
	@RequestMapping(value="/noticeWriteForm")
	public ModelAndView openNoticeWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("noticeWriteForm");
		
		return mv;
	}
	
	@RequestMapping(value="/noticeWrite")
	public ModelAndView insertNotice(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/noticeList");
		
		noticeService.insertNotice(commandMap.getMap());
		
		return mv;
	}
	
	//�󼼺��� ���� ��Ʈ�ѷ� �ڵ�
	@RequestMapping(value="/noticeDetail")
	public ModelAndView openNoticeDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("noticeDetail");
		
		Map<String, Object> map = noticeService.selectNoticeDetail(commandMap.getMap());
		
		mv.addObject("map", map);
		
		return mv;
	}
	
	//�����ϱ� ���� ��Ʈ�ѷ� �ڵ�
	@RequestMapping(value="/noticeModifyForm")
	public ModelAndView openNoticeUpdate(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("noticeModifyForm");
		
		Map<String, Object> map = noticeService.selectNoticeDetail(commandMap.getMap());
		mv.addObject("map",map);
		
		return mv;
	}
	
	@RequestMapping(value="/noticeModify")
	public ModelAndView updateNotice(CommandMap commandMap) throws Exception {
		ModelAndView mv= new ModelAndView("redirect:/noticeDetail");
		
		noticeService.updateNotice(commandMap.getMap());
		
		mv.addObject("NOTICE_NUMBER", commandMap.get("NOTICE_NUMBER"));
		return mv;
	}
	
	@RequestMapping(value="/noticeDelete")
	public ModelAndView deleteNotice(CommandMap commandMap) throws Exception {
		ModelAndView mv= new ModelAndView("redirect:/noticeList");
		
		noticeService.deleteNotice(commandMap.getMap());
		
		return mv;
	}
	


}
