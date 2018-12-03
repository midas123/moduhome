package com.kh.collecting;

import java.util.Date;

public class CollectingModel {

	private int collecting_seq;
	private int mg_number;
	private int member_number;
	private Date COLLECTING_DATE;
	
	

	public int getCollecting_seq() {
		return collecting_seq;
	}
	public void setCollecting_seq(int collecting_seq) {
		this.collecting_seq = collecting_seq;
	}
	public int getMg_number() {
		return mg_number;
	}
	public void setMg_number(int mg_number) {
		this.mg_number = mg_number;
	}
	public int getMember_number() {
		return member_number;
	}
	public void setMember_number(int member_number) {
		this.member_number = member_number;
	}
	public Date getCOLLECTING_DATE() {
		return COLLECTING_DATE;
	}
	public void setCOLLECTING_DATE(Date cOLLECTING_DATE) {
		COLLECTING_DATE = cOLLECTING_DATE;
	}
	

	
}
