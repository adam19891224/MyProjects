package com.adam.Designs.behaviour.MediatorCase.customer;
/**
 * 中介者模式作业
 * 模拟租房客和中介商之间的关系
 * @author adam
 * 2015.5.29
 */
public interface Customer {

	//内部包含两个方法，一个是我租到房子了，另一个是我没租到
	public void getHouse();
	
	public void lostHouse();
	
}
