package com.adam.Interest.jdk5.Generic.genericClass.main;

import com.adam.Interest.jdk5.Generic.genericClass.entity.DadGenTest;
import com.adam.Interest.jdk5.Generic.genericClass.entity.GenTest;
import com.adam.Interest.jdk5.Generic.genericClass.entity.SunGenTest;

/**
 * 泛型类的使用
 * @author adam
 * 2015.5.25
 */
public class Main {

	public static void main(String[] args) {
		GenTest<String> genString = new GenTest<String>("aaa");
		System.out.println(genString.getT());
		
		GenTest<Integer> genInt = new GenTest<Integer>(1);
		System.out.println(genInt.getT());
		
		//接下来是泛型继承的使用
		SunGenTest<String, String> sunGenTest = new SunGenTest<String, String>("aaa", "bbb");
		System.out.println(sunGenTest.getS() + "   " + sunGenTest.getT());
		SunGenTest<Integer, Integer> sunGenTest2 = new SunGenTest<Integer, Integer>(11, 22);
		System.out.println(sunGenTest2.getS() + "   " + sunGenTest2.getT());
		//SunGenTest<String, Object> sunGenTest2 = new SunGenTest<String, Object>();//此时就报错了，因为object并不是我们定义的comparalbe的子类
		/**
		 * 如上面代码所示，此时我们创建的是String, String类型，由于String自然是comparable的子类，所以编译通过
		 * 但是下面的代码，String, Object类型时，由于Object不是comparable的子类，所以编译报错
		 */
		
		
		//然后是指定父类的泛型继承：
		DadGenTest<String, Double> dadGenTest = new DadGenTest<String, Double>("aaa", 3.3);
		System.out.println(dadGenTest.getT() + "   " + dadGenTest.getK());
		//DadGenTest<String, Integer> dadGenTest2 = new DadGenTest<String, Integer>("aaa", 3.3);//此时就报错了，因为我们定义的是? super Double，但是这里我们是integer,integer显然不是double的子类，所以报错
		
	}
	
}
