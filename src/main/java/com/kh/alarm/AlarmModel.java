package com.kh.alarm;

import java.util.Date;

public class AlarmModel {

	private int alarm_seq;
	private int alarm_index_no;
	private int alarm_contnum;
	private String alarm_id;
	private String alarm_reg_id;
	private Date alarm_regdate;
	private Date alarm_readdate;
	
	public int getAlarm_seq() {
		return alarm_seq;
	}
	public void setAlarm_seq(int alarm_seq) {
		this.alarm_seq = alarm_seq;
	}
	public int getAlarm_index_no() {
		return alarm_index_no;
	}
	public void setAlarm_index_no(int alarm_index_no) {
		this.alarm_index_no = alarm_index_no;
	}
	public int getAlarm_contnum() {
		return alarm_contnum;
	}
	public void setAlarm_contnum(int alarm_contnum) {
		this.alarm_contnum = alarm_contnum;
	}
	public String getAlarm_id() {
		return alarm_id;
	}
	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
	}
	public String getAlarm_reg_id() {
		return alarm_reg_id;
	}
	public void setAlarm_reg_id(String alarm_reg_id) {
		this.alarm_reg_id = alarm_reg_id;
	}
	public Date getAlarm_regdate() {
		return alarm_regdate;
	}
	public void setAlarm_regdate(Date alarm_regdate) {
		this.alarm_regdate = alarm_regdate;
	}
	public Date getAlarm_readdate() {
		return alarm_readdate;
	}
	public void setAlarm_readdate(Date alarm_readdate) {
		this.alarm_readdate = alarm_readdate;
	}

	
	
}
