package com.adam.Designs.behaviour.Commond.interfaces.impl;

import com.adam.Designs.behaviour.Commond.interfaces.Commond;
import com.adam.Designs.behaviour.Commond.reciver.Reciver;

/**
 * 命令实际对象类
 */
public class ConcreteCommond implements Commond {
	
	//这就是实际命令接收类，这里我们还需要额外定义两个成员，一个是命令接收者的对象声明，即命令执行对象， 和传入命令执行对象的方法，这里我们采用构造方法传参的形式
	private Reciver reciver;
	
	//构造方法，当创建这个实际命令对象时，需要传入命令接受者对象
	public ConcreteCommond(Reciver r){
		this.reciver = r;
	}

	@Override
	public void excute() {
		//然后再命令执行方法中，调用接受者的相应处理方法即可
		reciver.say();
	}

}
