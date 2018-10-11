package com.kh.alarm;

import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("alarmService")
public class AlarmServiceImpl implements AlarmService {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "alarmDAO")
	private AlarmDAO alarmDAO;
	
	@Override
	public void regAlarm(int writer_id, int reg_id, int alarm_contnum, int alarm_index_no) throws Exception{
		System.out.println("알람서비스 실행");
		alarmDAO.regAlarm(writer_id, reg_id, alarm_contnum, alarm_index_no);
	}
	
	@Override
	public int alarmExist(String id) throws Exception{
		return alarmDAO.alarmExist(id);
	}

	@Override
	public List<AlarmModel> alarmLoad(String id) throws Exception{
		return alarmDAO.alarmLoad(id);
	}
	
	@Override
	public void readAlarm(String alarm_seq) throws Exception{
		alarmDAO.readAlarm(alarm_seq);
	}
	
	@Override
	public AlarmModel selectAlarmOne(String alarm_seq) throws Exception{
		return alarmDAO.selectAlarmOne(alarm_seq);
	}

	@Override
	public int alarm_reg_id_num(String reg_alarm_id) throws Exception {
		// TODO Auto-generated method stub
		return alarmDAO.alarm_reg_id_num(reg_alarm_id);
	}

}
