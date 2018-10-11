package com.kh.alarm;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlarmController {

	@Resource(name="alarmService")
	private AlarmService alarmService;
	
	@RequestMapping(value="/myalarm")
	public ModelAndView myalarm(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myalarm");
		
		return mav;
	}
	
	@RequestMapping(value="/alarmRead")
	public ModelAndView alarmRead(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		String alarm_seq = request.getParameter("alarm_seq");
		
		alarmService.readAlarm(alarm_seq);
		AlarmModel alarmModel = alarmService.selectAlarmOne(alarm_seq);
		
		String url = "";
		if(alarmModel.getAlarm_index_no() == 1 ){
			url = "snslist?SNS_NUMBER="+alarmModel.getAlarm_contnum();
		}
		else if(alarmModel.getAlarm_index_no() == 2){
			int alarm_reg_id = alarmService.alarm_reg_id_num(alarmModel.getAlarm_reg_id());
			url = "myHome?MEMBER_NUMBER="+alarm_reg_id;
		}
		else if(alarmModel.getAlarm_index_no() == 3){
			url = "snslist?SNS_NUMBER="+alarmModel.getAlarm_contnum();
		}
		else if(alarmModel.getAlarm_index_no() == 4){
			url = "snslist?SNS_NUMBER="+alarmModel.getAlarm_contnum();
		}		
		mav.setViewName("redirect:"+url);
		
		List<AlarmModel> mem_alarm = null;
		if(alarmService.alarmExist(alarmModel.getAlarm_id()) != 0){
			mem_alarm = alarmService.alarmLoad(alarmModel.getAlarm_id());
			System.out.println("mem_id ? "+alarmModel.getAlarm_id());
			
		}
		

	    HttpSession session = request.getSession();
		//System.out.println("mem_alarm의 크기는? " + mem_alarm.size());
		session.setAttribute("session_mem_alarm", mem_alarm);
		
		
		return mav;
	}
	
}
