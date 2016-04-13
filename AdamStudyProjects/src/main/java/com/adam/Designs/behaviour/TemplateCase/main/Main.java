package com.adam.Designs.behaviour.TemplateCase.main;

import com.adam.Designs.behaviour.TemplateCase.interfaces.LogSys;
import com.adam.Designs.behaviour.TemplateCase.interfaces.impl.LinuxLogs;
import com.adam.Designs.behaviour.TemplateCase.interfaces.impl.MacLogs;
import com.adam.Designs.behaviour.TemplateCase.interfaces.impl.WinLogs;

public class Main {

	public static void main(String[] args) {
		
		//假设此时是windows系统
		Windows windows = new Windows();
		windows.showLog();
		
		//假设此时是linux系统
		Linux linux = new Linux();
		linux.showLog();
		
		//假设此时是mac系统
		Mac mac = new Mac();
		mac.showLog();
		
	}
	
}

class Windows{//windows内部类
	
	private LogSys logSys = new WinLogs();
	
	public void showLog(){
		this.logSys.showLog();
	}
	
}

class Linux{//linux内部类
	
	private LogSys logSys = new LinuxLogs();
	
	public void showLog(){
		this.logSys.showLog();
	}
	
}

class Mac{//mac内部类
	
	private LogSys logSys = new MacLogs();
	
	public void showLog(){
		this.logSys.showLog();
	}
	
}