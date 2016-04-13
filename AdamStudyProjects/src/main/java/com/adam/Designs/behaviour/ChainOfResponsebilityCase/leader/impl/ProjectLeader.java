package com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.impl;

import com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.Leader;

/**
 * 项目经理
 *
 */
public class ProjectLeader implements Leader {

	//创建一个上级领导对象
	private Leader upLeader;

	//处理请求的方法
	public void dealApply(String emp, Long price) {
		//由于这是小张的声请，如果是其他人冒声请，则直接打回
		if(emp.equals("小张")){
			//如果小于300，则项目经理可以直接批准
			if(price <= 300){
				System.out.println(emp + " 你好，你声请的   " + price + " 聚餐费已经经过了审核-------项目经理");
			}else{
				//则需要交给部门经理
				if(this.getUpLeader() != null){
					System.out.println("项目经理不能处理这个声请，需要部门经理来处理");
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
