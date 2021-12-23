package com.records;

public class Staff {
	public int id;
	public String name, position, contact, address;
	public float salary;
	public Staff(int id, String name, String position, float salary, String contact, String address){
		this.id=id;
		this.name=name;
		this.position=position;
		this.salary=salary;
		this.contact=contact;
		this.address=address;
	}
}
