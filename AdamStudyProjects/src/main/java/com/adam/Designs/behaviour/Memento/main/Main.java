package com.adam.Designs.behaviour.Memento.main;

import com.adam.Designs.behaviour.Memento.master.MasterMan;
import com.adam.Designs.behaviour.Memento.mementotaker.MementoTaker;

/**
 * 备忘录模式演示
 * @author adam
 * 2015.6.1
 */
public class Main {

	public static void main(String[] args) {
		
		//我们定义中说过，用户不会直接操作备忘录，而是操作备忘录操作者，而具体的备忘录操作由这个操作者来完成，所以这里我们就只需要创建两个对象即可，一个是主人，另一个是操作者
		MasterMan man = new MasterMan();
		MementoTaker taker = new MementoTaker();
		
		//主人此时需要记录一些信息,我们直接创建备忘录即可
		//然后由备忘录操作者来负责保存这个备忘录
		taker.saveMemento(man.createMemento("今晚不加班"));
		
		//然后此时调用man的做事方法，表示此时主人正在做其他事
		man.doThing();
		
		//此时主人想起了什么事，需要从备忘录中得到信息，当然，还是要交给备忘录操作者
		String info = man.getMementoInfo(taker.getMemento());
		System.out.println(info);
	}
	
	/*
	 * 总结：
	 * 这就是备忘录模式
	 * 我们发现，我们可以在任何时候保存一个对象的信息，然后对象就可以去做其他事了，当我们需要得到对象保存的信息时，通过备忘录对象记录的信息，就能够得到我们之前保存的信息了。
	 * 就真的好像备忘录一样。
	 */
	
}
