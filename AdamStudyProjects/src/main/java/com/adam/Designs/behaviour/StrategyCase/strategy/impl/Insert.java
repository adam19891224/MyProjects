package com.adam.Designs.behaviour.StrategyCase.strategy.impl;

import com.adam.Designs.behaviour.StrategyCase.strategy.Strategy;

/**
 * 插入排序
 *
 */
public class Insert implements Strategy {

	@Override
	public void excute() {
		System.out.println("我是插入排序");
	}

}
