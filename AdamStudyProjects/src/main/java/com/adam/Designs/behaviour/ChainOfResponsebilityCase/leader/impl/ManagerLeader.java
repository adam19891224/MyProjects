package com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.impl;

import com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.Leader;

/**
 * 总经理
 *
 */
public class ManagerLeader implements Leader {

	//创建一个上级领导对象，虽然总经理没有上级对象，但是我们也要定义，保证责任链的完整性
	private Leader upLeader;

	//处理请求的方法
	public void dealApply(String emp, Long price) {
		//由于这是小张的声请，如果是其他人冒声请，则直接打回
		if(emp.equals("小张")){
			//总经理批准任何大小的经费
			if(price > 800){
				System.out.println(emp + " 你好，你声请的   " + price + " 聚餐费已经经过了审核------总经理");
			}else{
				//则需要交给总经理的上级经理，显然这里是没有了，但是我们说了，为了保证责任链的完整性，还是要这样写
				if(this.getUpLeader() != null){
					System.out.println("总经理不能处理这个声请，已经没有人能处理这个事情了");
					this.getUpLeader().dealApply(emp, price);//然后让这个上级领导来处理这个请求
				}
			}
		}else{
			System.out.println("当前用户是: " + emp + "，不是小张，驳回声请");
		}
	}
	
	//传递下一个上级领导
	public void setUpLeader(Leader leader){
		this.upLeader = leader;
	}
	
	//得到当前经理的上级领导是否存在
	public Leader getUpLeader(){
		return this.upLeader;
	}
	
}
