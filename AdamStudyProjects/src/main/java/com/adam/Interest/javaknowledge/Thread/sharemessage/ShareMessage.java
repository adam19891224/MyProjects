package com.adam.Interest.javaknowledge.Thread.sharemessage;

public class ShareMessage {

	public static void main(String[] args) {
		
		Resource r = new Resource();
		Input in = new Input(r);
		Output out = new Output(r);
		
		//创建两个线程对象
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		
		t1.start();
		t2.start();
		
	}
	
}

class Resource{
	public String name;
	
	public String age;
	
	public boolean isShow = false;
	
	//修改代码
	public synchronized void set(String name, String age){
		if(isShow){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.name = name;
		this.age = age;
		isShow = true;
		this.notify();
	}
	
	public synchronized void show(){
		if(isShow){
			System.out.println("name: ----" + name + "    age: -----" + age);
			isShow = false;
			this.notify();
		}
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//输入对象
class Input implements Runnable{

	Resource r = null;
	
	public Input(Resource r) {
		this.r = r;
	}
	
	int change = 0;//定义一个切换标识
	
	@Override
	public void run() {
		//当然需要循环来无限赋值
		while (true) {
//			synchronized (r) { 
//				
//				if(r.isShow){
//					try {
//						r.wait();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				//为了区别，我们用标识来切换，当change为0时，赋值为中文
//				if(change == 0){
//					r.name = "小张";
//					r.age = "22岁";
//				}else{//change不为0时，赋值为英文
//					r.name = "Mary";
//					r.age = "twinty";
//				}
//				//切换状态， 用取2的模来做0  1切换
//				change = (change + 1) % 2;//当change=0时，执行，change=1，当change=1时，执行, change=0
//				
//				//切换状态
//				r.isShow = true;//表示此时可以展示了
//				r.notify();//唤醒对方
//			}
			//为了区别，我们用标识来切换，当change为0时，赋值为中文
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(change == 0){
				r.set("小张", "22岁");
			}else{//change不为0时，赋值为英文
				r.set("Mary", "twinty");
			}
			//切换状态， 用取2的模来做0  1切换
			change = (change + 1) % 2;//当change=0时，执行，change=1，当change=1时，执行, change=0
		}
	}
}

class Output implements Runnable{

	Resource r = null;
	
	public Output(Resource r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		//当然是循环打印
		while(true){
//			synchronized (r) {
//				
//				if(r.isShow){
//					System.out.println("name: ----" + r.name + "    age: -----" + r.age);
//					//同时切换
//					r.isShow = false;
//					r.notify();//唤醒对方
//				}
//				//当不为true时，冻结
//				try {
//					r.wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
			r.show();
		}
	}
	
}
