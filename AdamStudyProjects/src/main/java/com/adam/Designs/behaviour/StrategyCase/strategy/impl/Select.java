package com.adam.Designs.behaviour.StrategyCase.strategy.impl;

import com.adam.Designs.behaviour.StrategyCase.strategy.Strategy;

/**
 * 选择排序
 *
 */
public class Select implements Strategy {

	@Override
	public void excute() {
		System.out.println("我是选择排序");
	}

}
