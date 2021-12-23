package com.records;

public class Patients {
	public int id;
	public String name, roomNo, contact, sex, history;
	public Patients(int id, String roomNo, String name, String contact, String sex, String history) {
		this.id = id;
		this.name = name;
		this.roomNo = roomNo;
		this.contact = contact;
		this.sex = sex;
		this.history = history;
	}
}
