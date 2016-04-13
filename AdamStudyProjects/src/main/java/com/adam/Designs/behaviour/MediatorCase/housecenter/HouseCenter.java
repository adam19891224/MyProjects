package com.adam.Designs.behaviour.MediatorCase.housecenter;

import com.adam.Designs.behaviour.MediatorCase.customer.Customer;

/**
 * 房产中介
 *
 */
public interface HouseCenter {

	//内部包含两个发布消息的方法，当有人租房子时，就发布给所有的租房客,传入的参数是当前租到房子的用户
	public void publishMessage(Customer customer);
	//登记方法，表示当前客户是该中介公司的一个顾客
	public void loginIn(Customer customer);
	
}
