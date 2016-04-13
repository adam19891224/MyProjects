package com.adam.Designs.behaviour.VisitorCase.main;

import com.adam.Designs.behaviour.VisitorCase.interfaces.Controller;
import com.adam.Designs.behaviour.VisitorCase.interfaces.JDBCService;
import com.adam.Designs.behaviour.VisitorCase.interfaces.impl.Controllers;
import com.adam.Designs.behaviour.VisitorCase.interfaces.impl.FilterControllers;
import com.adam.Designs.behaviour.VisitorCase.interfaces.impl.ProgramService;
import com.adam.Designs.behaviour.VisitorCase.interfaces.impl.UserService;

public class Main {

	public static void main(String[] args) {
		
		//创建两个元素实现类
		JDBCService user = new UserService();
		JDBCService program = new ProgramService();
		
		//创建访问者，即controller
		Controller c = new Controllers();
		
		user.accept(c);
		program.accept(c);
		
		//但是此时我们有一个需求，就是筛选出所有Program中以j开头的所有语言，那么又怎么做呢？
		
		//当然，我们可以进入programservice中直接进行筛选，但是这样就违背了我们的初衷，接口原则： 对扩展开放，对修改关闭
		//因为我们这样就采用了直接修改service的方式了，与对修改关闭冲突
		//所以此时更简单的做法就是继续扩展一个controller, 这个controller取名为FilterController
		
		Controller fc = new FilterControllers();
		//由于是筛选Program中所有以j开头的，所以只需要把这个访问者传入programservice即可
		System.out.println("============================传入筛选访问者=================");
		program.accept(fc);
		user.accept(fc);
		
		
	}
	
}
