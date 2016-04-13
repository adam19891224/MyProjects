package com.adam.Designs.behaviour.MementoCase.main;

import com.adam.Designs.behaviour.MementoCase.housekeeper.Keeper;
import com.adam.Designs.behaviour.MementoCase.master.Master;

/**
 * 备忘录模式作业
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//创建主人和备忘录操作者，即管家
		Master master = new Master();
		Keeper misslady = new Keeper();
		
		//记录一条备忘录,交给管家miss女士保管
		misslady.saveRecord(master.createRecord("一", "好好加油"));
		misslady.saveRecord(master.createRecord("二", "今晚不加班"));
		
		//做其他事
		System.out.println("主人正在做其他事");
		
		//得到备忘录
		String record = master.getRecord("一", misslady.getBook());
		System.out.println(record);
		
		
	}
	
}
