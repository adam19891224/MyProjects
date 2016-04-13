package com.adam.Designs.behaviour.MediatorCase.customer.impl;

import com.adam.Designs.behaviour.MediatorCase.customer.Customer;
import com.adam.Designs.behaviour.MediatorCase.housecenter.HouseCenter;

public class Teacher implements Customer {

	public Teacher(HouseCenter center){
		center.loginIn(this);
	}

	@Override
	public void getHouse() {
		System.out.println("嘻嘻，我是老师，我租到房子了");
	}

	@Override
	public void lostHouse() {
		System.out.println("呜，我是老师，我没有租到房子");
	}
	
}
