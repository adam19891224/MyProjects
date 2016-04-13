package com.adam.Designs.behaviour.VisitorCase.interfaces.impl;

import java.util.List;

import com.adam.Designs.behaviour.VisitorCase.interfaces.Controller;
import com.adam.Designs.behaviour.VisitorCase.interfaces.JDBCService;
import com.adam.Utils.Utils;

public class ProgramService implements JDBCService {

	@SuppressWarnings("static-access")
	@Override
	public List<String> select() {
		List<String> s = new Utils().getList();
		s.add("java");
		s.add("c#");
		s.add("scala");
		s.add("python");
		s.add("php");
		s.add("ruby");
		s.add("javascript");
		return s;
	}

	@Override
	public void accept(Controller c) {
		c.getString(this);
	}

}
