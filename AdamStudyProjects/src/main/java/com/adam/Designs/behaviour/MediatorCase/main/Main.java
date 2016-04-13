package com.adam.Designs.behaviour.MediatorCase.main;

import com.adam.Designs.behaviour.MediatorCase.customer.Customer;
import com.adam.Designs.behaviour.MediatorCase.customer.impl.Engineer;
import com.adam.Designs.behaviour.MediatorCase.customer.impl.Teacher;
import com.adam.Designs.behaviour.MediatorCase.customer.impl.Worker;
import com.adam.Designs.behaviour.MediatorCase.housecenter.HouseCenter;
import com.adam.Designs.behaviour.MediatorCase.housecenter.impl.Center;

/**
 * 中介者模式作业
 * @author adam
 * 2015.5.29
 *
 */
public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//创建中心
		HouseCenter center = new Center();
		
		//创建三个客户
		Customer worker = new Worker(center);
		Customer teacher = new Teacher(center);
		Customer engineer = new Engineer(center);
		
		//此时我们让工人租到房子
		center.publishMessage(worker);
		
	}
	
}
