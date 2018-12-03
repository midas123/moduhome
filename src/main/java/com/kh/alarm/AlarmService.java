package com.kh.alarm;

import java.util.List;

public interface AlarmService {

	void regAlarm(int writer_id, int reg_id, int alarm_contnum, int alarm_index_no) throws Exception;
	
	int alarmExist(String id) throws Exception;
	
	List<AlarmModel> alarmLoad(String id) throws Exception;
	
	void readAlarm(String alarm_seq) throws Exception;
	
	AlarmModel selectAlarmOne(String alarm_seq) throws Exception;
	
	int alarm_reg_id_num(String reg_alarm_id) throws Exception;
}
