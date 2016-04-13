package com.adam.Designs.behaviour.Template.main;

import com.adam.Designs.behaviour.Template.interfaces.Template;
import com.adam.Designs.behaviour.Template.interfaces.impl.ConTemplate1;
import com.adam.Designs.behaviour.Template.interfaces.impl.ConTemplate2;

/**
 * 模板模式演练
 * @author adam
 * 2015.6.4
 */
public class Main {

	public static void main(String[] args) {
		
		//此时创建1号模板
		Template template1 = new ConTemplate1();
		
		template1.showLog();
		
		//创建2号模板
		Template template2 = new ConTemplate2();
		template2.showLog();
		
	}
	
}
