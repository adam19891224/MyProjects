package com.adam.Interest.javaknowledge.Concurrent.countdowmlatch.main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.adam.Interest.javaknowledge.Concurrent.countdowmlatch.CountDownLatchThread;

/**
 * 计数器线程演示
 *
 */
public class Main {

	public static void main(String[] args) {
		//首先创建计数器
		CountDownLatch latch = new CountDownLatch(10);//由于需求是10个线程，所以这里定义数字为10
		CountDownLatch start = new CountDownLatch(11);//创建一个发令线程，作用是在for循环之后调用，即当for循环的线程创建完毕之后，只会输出一个线程xx准备完毕的语句，当10个线程创建完毕后，我们让start来countdown，就像发令枪一样,这里创建11是因为在各自线程中，调用countdown，表示当前线程准备完毕，所有线程准备完毕之后，下面在发出总命令
		ExecutorService executor = Executors.newFixedThreadPool(10);//创建一个线程池
		
		//创建10个线程，并调用他们的start方法启动
		for(int a = 0; a < 10; a++){
			CountDownLatchThread downLatchThread = new CountDownLatchThread(latch, start, a);
			//传递给线程对象，表示此对象是线程
			Thread thread = new Thread(downLatchThread);
			executor.submit(thread);//相当于执行线程的start方法
		}
		
		//开始发令,执行到这里时，start计数器的数字必然是1，然后调用最后一次计数，释放他们
		start.countDown();
		
		/*
		 * 此时如果不让这个main方法挂起，而直接打印线程完毕，那么你会看见和我们预期的完全不一样,你会发现所有线程执行完毕的话放到了其他线程的打印语句之间:
		 * 线程0号：  打印语句hello world
		 * 线程1号：  打印语句hello world
		 * 线程8号：  打印语句hello world
		 * 所有线程执行完毕
		 * 线程2号：  打印语句hello world
		 * 
		 * 而我们预期希望的是所有线程打印完hello之后，在打印这句话，所以此时我们就需要在这句话执行采用挂起方法，调用await()方法挂起
		 */
		try {
			latch.await();
			//当其他线程打印完毕并且把数字减为0之后，才会执行下面的打印语句
			System.out.println("所有线程执行完毕");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();//表示线程池不在接收新的线程，如果此时线程池中有剩余线程，就执行完,还有一个shutdonwNow方法，即立即中断
	}
	
	/*
	 * 此时我们做一件有趣的事情，把上面的计数器改为3，看看有什么效果,
	 * 此时我们发现，所有线程执行完毕的方法又出现在了helloworld语句之间，这次难道又是和上面一样的问题吗？答案显然不是，因为此时我们的计数器定义的是3，那么当三个线程执行完打印之后，
	 * 此时这个数字就变成0了，所以await方法就被释放，而执行后面的打印语句，但是此时是不是还有7个线程并没有执行完毕呀？所以当然这7个线程的打印语句就会出现在后面了
	 * 
	 * 那么此时我们把数字改为11看看，相信大家已经能够推理出来了，由于我们只有10个线程，所以执行countdown的方法只能被调用10次，那么计数器最后的数字就是1，而await只有在数字是0的时候
	 * 才会被释放，所以这也就意味着计数器的await方法将永远不会被释放了，那么也就意味着我们等一辈子也不会看到所有现场执行完毕的语句被打印出来
	 * 
	 */
}
