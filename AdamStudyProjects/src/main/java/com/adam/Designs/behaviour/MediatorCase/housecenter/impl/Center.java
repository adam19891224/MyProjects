package com.adam.Designs.behaviour.MediatorCase.housecenter.impl;

import java.util.List;

import com.adam.Designs.behaviour.MediatorCase.customer.Customer;
import com.adam.Designs.behaviour.MediatorCase.customer.impl.Worker;
import com.adam.Designs.behaviour.MediatorCase.housecenter.HouseCenter;
import com.adam.Utils.Utils;

/**
 * 中介中心实现类
 *
 */
public class Center implements HouseCenter {
	
	//定义一个list来管理所有的客户
	private final List<Customer> list = Utils.getList();

	@Override
	public void publishMessage(Customer customer) {
		//循环当前的客户集合，判断是否满足当前传入的客户, 这里假设是工人租到房子
		for(Customer c : this.list){
			if(c instanceof Worker){
				c.getHouse();
			}else{
				c.lostHouse();
			}
		}
	}

	@Override
	public void loginIn(Customer customer) {
		this.list.add(customer);
	}

}
