package com.adam.Designs.behaviour.TemplateCase.interfaces.impl;

import com.adam.Designs.behaviour.TemplateCase.interfaces.LogSys;

/**
 * Mac版日志系统
 * @author Administrator
 *
 */
public class MacLogs implements LogSys {

	@Override
	public void showLog() {
		System.out.println("Mac版日志系统");
	}

}
