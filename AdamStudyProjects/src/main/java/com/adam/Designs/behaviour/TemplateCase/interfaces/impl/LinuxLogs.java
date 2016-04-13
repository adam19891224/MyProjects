package com.adam.Designs.behaviour.TemplateCase.interfaces.impl;

import com.adam.Designs.behaviour.TemplateCase.interfaces.LogSys;

/**
 * linux版日志系统
 *
 */
public class LinuxLogs implements LogSys {

	@Override
	public void showLog() {
		System.out.println("linux版日志系统");
	}

}
