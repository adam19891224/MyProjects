package com.adam.Designs.behaviour.StrategyCase.strategy.impl;

import com.adam.Designs.behaviour.StrategyCase.strategy.Strategy;

/**
 * 冒泡策略
 *
 */
public class Bubbling implements Strategy {

	@Override
	public void excute() {
		System.out.println("我是冒泡排序");
	}

}
