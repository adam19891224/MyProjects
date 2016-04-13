package com.adam.Designs.behaviour.MediatorCase.customer.impl;

import com.adam.Designs.behaviour.MediatorCase.customer.Customer;
import com.adam.Designs.behaviour.MediatorCase.housecenter.HouseCenter;

/**
 * 租房客实际类，这是工人
 *
 */
public class Worker implements Customer {

	//构造方法，传入中介中心
	public Worker(HouseCenter center){
		center.loginIn(this);
	}

	@Override
	public void getHouse() {
		System.out.println("呵呵，我是工人，我租到房子了");
	}

	@Override
	public void lostHouse() {
		System.out.println("唉，我是工人，我没租到房子");
	}
	
	
}
