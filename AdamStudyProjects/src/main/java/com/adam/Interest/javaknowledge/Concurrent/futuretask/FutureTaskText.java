package com.adam.Interest.javaknowledge.Concurrent.futuretask;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * concurrent 将来任务演示
 * 这个名字看似不太明白，没事，我们用一个实际案例来解释就明白了
 * 所谓FutureTask，意思就是我们此时有一个线程，这个线程执行结果会比较漫长，除此之外，当前线程还有其他事项要做，但是这个漫长的线程的返回结果
 * 又是我们所需要的，那么此时这个漫长的线程就可以用futuretask来修饰，这里你可能会说，我主线程另外启动一个线程，主线程依旧能做其他的事啊，没错，但是此时我们希望主线程得到这个漫长线程
 * 的返回结果，然后根据返回结果，再继续处理，此时你如果用常规方式就没有办法了，举个例子
 * 
 * 此时一个银行工作人员，帮你办卡，此时你存入50W人民币，那么当你提交这个声请时，银行的电脑肯定不会立即就给你完成，总会等待，而此时银行工作人员肯定会叫你做其他事情，例如填表什么的，而此时表中有一行记录
 * 为存入是否成功的字段，这个字段又必须要等待电脑的处理结果，所以此时这个电脑处理就可以放在futuretask中，当我们提交申请后，就是一个主线程开启，然后经过一系列交谈之后，工作人员开始对电脑进行提交审核，此时就
 * 相当于另开一个线程用futruetask封装，然后继续做我们的事情，例如填表什么的，当我填到是否成功的这一行时，由于没有结果，就又需要等电脑的处理，当电脑处理完毕之后，我就可以继续填表，然后完成这个流程了，这就是
 * futuretask的作用，如果用一般的线程来处理，你可能这辈子都等不到返回结果了
 * @author adam
 * 2015.5.28
 *
 */
public class FutureTaskText implements Callable<Object> {
	
	//此时我们就模拟上面的这个流程
	//这是我们的电脑处理过程,由于要返回，所以就不能实现runable了，而是callable

	//处理钱的方法
	private String excute(){
		try {
			System.out.println("提交请求成功，等待处理...");
			Thread.sleep(1000 * new Random().nextInt(20));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "声请成功处理，恭喜您";
	}
	
	@Override
	public Object call() throws Exception {
		return this.excute();
	}
	
}
