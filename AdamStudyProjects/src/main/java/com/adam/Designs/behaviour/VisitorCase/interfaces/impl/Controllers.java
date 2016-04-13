package com.adam.Designs.behaviour.VisitorCase.interfaces.impl;

import java.util.List;

import com.adam.Designs.behaviour.VisitorCase.interfaces.Controller;

public class Controllers implements Controller {

	@Override
	public void getString(UserService s) {
		List<String> res = s.select();
		res.forEach((String e) -> System.out.println(e));
	}

	@Override
	public void getString(ProgramService s) {
		s.select().forEach( r -> System.out.println(r));
	}

}
