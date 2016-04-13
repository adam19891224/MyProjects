package com.adam.Designs.behaviour.Strategy.strategy.impl;

import com.adam.Designs.behaviour.Strategy.strategy.Strategy;

/**
 * 策略1
 */
public class ConStrategy1 implements Strategy {

	@Override
	public void excute() {
		
		System.out.println("执行策略1");
	}

}
