package com.adam.Designs.behaviour.VisitorCase.interfaces.impl;

import com.adam.Designs.behaviour.VisitorCase.interfaces.Controller;

public class FilterControllers implements Controller {

	@Override
	public void getString(UserService s) {
		s.select().stream().
					filter(r -> (r.indexOf("周") != -1)).
					forEach(r -> System.out.println(r));
	}

	@Override
	public void getString(ProgramService s) {
		
		//此时我们就需要过滤条件了，即所有j开头的,这里我们用lambda来做
//		Predicate<String> startJ = n -> n.startsWith("j");
		s.select().stream().//首先得到stream流，这是java8的一个对象
				   filter( r -> r.startsWith("j") ).//然后创建过滤条件 过滤条件是一个Predicate对象，我们当然可以创建一个这个对象, 如上面的startJ, 然后在filter中传入startJ即可
				   forEach( r -> System.out.println(r));//遍历并打印
		
	}

}
