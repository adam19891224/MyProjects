package com.adam.Designs.behaviour.VisitorCase.interfaces;

import com.adam.Designs.behaviour.VisitorCase.interfaces.impl.ProgramService;
import com.adam.Designs.behaviour.VisitorCase.interfaces.impl.UserService;

public interface Controller {

	//这是访问者， 提供一个访问方法
	void getString(UserService s);
	
	void getString(ProgramService s);
}
