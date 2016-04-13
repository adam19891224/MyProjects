package com.adam.Designs.behaviour.VisitorCase.interfaces.impl;

import java.util.List;

import com.adam.Designs.behaviour.VisitorCase.interfaces.Controller;
import com.adam.Designs.behaviour.VisitorCase.interfaces.JDBCService;
import com.adam.Utils.Utils;

public class UserService implements JDBCService {

	@SuppressWarnings("static-access")
	@Override
	public List<String> select() {
		List<String> s = new Utils().getList();
		s.add("小张");
		s.add("小王");
		s.add("小李");
		s.add("小周");
		return s;
	}

	@Override
	public void accept(Controller c) {
		c.getString(this);
	}

}
