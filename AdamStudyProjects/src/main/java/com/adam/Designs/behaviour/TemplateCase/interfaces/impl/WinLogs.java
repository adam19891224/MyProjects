package com.adam.Designs.behaviour.TemplateCase.interfaces.impl;

import com.adam.Designs.behaviour.TemplateCase.interfaces.LogSys;

/**
 * windows版日志系统
 *
 */
public class WinLogs implements LogSys {

	@Override
	public void showLog() {
		System.out.println("windows版日志系统");
	}

}
