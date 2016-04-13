package com.adam.Test.TestBuilderConstractor.main;

import com.adam.Test.TestBuilderConstractor.entity.Entity;

public class Main {

	public static void main(String[] args) {
		//通过构建器，我们来创建一个entity对象，
		Entity entity = new Entity.Builder().age("13").name("zyh").create();
		System.out.println(entity.toString());
		
		//当然，我们也可以只传一个参数，十分灵活
		Entity ent = new Entity.Builder().age("15").create();
		System.out.println(ent);
		
		//此时我们发现，我们并没有用entity的构造方法，或者set方法来赋值，但是我们却能够得到entity的实例，并且得到我们赋予的初值，这都是因为
		//我们用了构建器，相当于构建器来给我们提供了entity的set方法，我们把值付给构建器之后，再把构建器传递给entity的私有构造方法中，然后
		//利用构建器来给entity赋值，当然，前提是要保证构建器的属性和entity的属性要一一对应才行
	}
	
}
