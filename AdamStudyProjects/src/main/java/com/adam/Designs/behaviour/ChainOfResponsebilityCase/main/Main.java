package com.adam.Designs.behaviour.ChainOfResponsebilityCase.main;

import com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.Leader;
import com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.impl.DepartLeader;
import com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.impl.ManagerLeader;
import com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader.impl.ProjectLeader;

/**
 * 责任链模式作业
 * 定义一个小张的员工，分别请求240， 680， 1500的经费，观察领导责任链是怎么处理的
 *
 */
public class Main {

	public static void main(String[] args) {
		//定义领导层，由项目经理，部门经理，总经理组成
		Leader proL = new ProjectLeader();
		Leader depL = new DepartLeader();
		Leader manL = new ManagerLeader();
		
		//项目经理需要传递下一个领导，即部门经理
		proL.setUpLeader(depL);
		//部门经理需要传递下一个领导，即总经理
		depL.setUpLeader(manL);
		
		//这里我们注意，此时我们先把整个责任链构造完成，然后再进行请求的发送，即使此时我们只声请100元，此时我们明知道项目经理就能处理请求
		//但是我们要优先构造责任链，所以要先进行传递
		
		//此时就开始进行请求声请了,我们员工的直接上级是项目经理，所以所有请求都是由项目经理接收
		
		//首先是小王，500，由于小王不是小张，自然被驳回
//		proL.dealApply("小王", 500l);
		
		//然后是小张，240元
		//proL.dealApply("小张", 240l);
		
		//然后是小张，680元
		//proL.dealApply("小张", 680l);
		
		//然后是小张，1500元
		proL.dealApply("小张", 1500l);
		
	}
	
}
