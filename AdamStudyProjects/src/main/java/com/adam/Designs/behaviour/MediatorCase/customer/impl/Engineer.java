package com.adam.Designs.behaviour.MediatorCase.customer.impl;

import com.adam.Designs.behaviour.MediatorCase.customer.Customer;
import com.adam.Designs.behaviour.MediatorCase.housecenter.HouseCenter;

public class Engineer implements Customer {

	public Engineer(HouseCenter center){
		center.loginIn(this);
	}
	
	@Override
	public void getHouse() {
		System.out.println("嘿嘿，我是工程师，我租到房子了");
	}

	@Override
	public void lostHouse() {
		System.out.println("呃，我是工程师，我没有租到房子");
	}

}
