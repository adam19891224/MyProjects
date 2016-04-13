package com.adam.Interest.javaknowledge.Exceptions.main;

import com.adam.Interest.javaknowledge.Exceptions.custom.entity.Entity;
import com.adam.Interest.javaknowledge.Exceptions.custom.exception.checked.CustomIoException;

/**
 * 异常测试
 * @author adam
 * 2015.5.28
 */
public class Main {

	public static void main(String[] args) {
//		Main.customRuntimeText();
//		Main.customIoText();
		System.out.println(Main.customIoText());
		/**
		 * 小结：
		 * 我们发现，java的异常分为unchecked异常和checked异常，这里我们定义了两个不同的异常：runtime以及io，java自定义异常的方式格式大致如下：
		 * public class 异常名字  extends Exception{
		 * 		//该异常的构造方法
		 * 		public 异常名字(String str){
		 * 			super(str);
		 * 		}
		 * 		//如果此时希望不带参数，也可以再写一个不带参数的异常
		 * 		public 异常名字(){
		 * 			super();
		 * 		}
		 * }
		 * 
		 * 此时我们定义了两个异常，一个继承自RuntimeException， 另一个继承自IoException，在java中，runtime异常就是Unchecked异常，而io异常就是checked异常
		 * 说了这么多，到底什么是unchecked异常，什么是checked异常呢？
		 * 所谓unchecked异常，就是不需要在编译器编译时检查的异常，例如下面的例子，我们调用抛出了runtime的异常，eclipse不会报任何错
		 * 而checked异常，就是指定编译器在编译时就要检查程序员是否对这个异常做出了解决办法（throw或者try-catch），所以下面的io异常例子，如果我们不用try-catch，eclipse
		 * 就会报错
		 * 并且从上面我们自定义的例子可以看出，异常总是先被抛出（在entity的throwIoText方法中），然后再被捕获（在这里main的customIoText方法中）。
		 * 
		 * 然后是finally部分，这部分并不是必须要加的，我们只用try--catch也可以，而这部分的意义就在于无论程序的try块部分执行完毕还是半途遇到异常进入catch块部分执行，最终都会
		 * 进入finally块部分，那么这个意义就十分大了，例如读取文件时，io异常是必须捕获的，而如果出现异常，我们的文件流没关闭就会造成内存开销，所以我们通常都把关闭文件的部分放在finally块中
		 * 这样如果try部分执行完毕，进入finally块关闭流，如果异常进入了catch块，那么执行完毕后，还是会进入finally块执行关闭流的代码，所以finally块还是很有意义的
		 * 并且注意，此时我们在finally块外部执行一个return的语句，看看执行顺序，其实很多人都想到了，肯定是先执行finally，再执行return ,不然先执行return 的话，哪里还有机会执行finally呢？
		 * 
		 * 但是要注意一点，finally只有在以下几种情况的时候，不会被执行：
		 * 1.finally中出现了异常，则不会执行
		 * 2.我们调用了System.exit()方法，让程序强制退出，则不会执行
		 * 3.程序所在的线程突然死亡，则不会执行
		 * 4.cpu中断等外界因素，则不会执行
		 * 
		 * 
		 * 
		 * 上面讲了捕获异常，接下来就讲解抛出异常throw
		 * 异常通常情况下，我们都是说的checked异常，因为Unchecked异常我们不用管，由jvm来处理也可以，但是checked异常就不同了，如果我们不管，编译器会报错，那么这一关我们都无法越过，还怎么做其他的事呢？
		 * 而上面的例子也看得很清楚，try-catch就是我们处理异常的一种方法，另一种就是throw了
		 * 就如同我们自定义的异常类， customIoException一样，里面的customIoText就是采用了抛出异常的方法来处理异常，所谓抛出异常，就是把这个异常抛给调用这个方法的人，而方法本身不会去处理，就像计算机出问题一样
		 * 它会报警，让人去检查并维修，而计算机本身不会自己维修异常，所以customIoText就像这里的计算机一样，它只负责抛出CustomIoException异常，但是自己并不做处理，而是让这里的Main中的ioText方法来处理，
		 * 当然，ioText依旧可以不用处理，继续向上抛出，则最终会由jvm来进行处理，所以抛出异常也体现了一点，就是无论怎么抛，必须要保证有一个类能够处理。
		 * 
		 * 
		 */
	}
	
	
	
	//测试自定义的runtime异常
	public static void customRuntimeText(){
		Entity entity = new Entity();
		entity.throwCustomRuntime();
	}
	
	//测试自定义的io异常
	public static String customIoText(){
		Entity entity = new Entity();
		try {
			entity.throwCustomIo();
		} catch (CustomIoException e) {
			System.out.println("调用方法的异常块，说明此时异常进入了这里");
			e.printStackTrace();
		} finally{
			System.out.println("执行finally块");
		}
		return "s";
	}
}
