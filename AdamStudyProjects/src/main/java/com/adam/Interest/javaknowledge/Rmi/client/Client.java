package com.adam.Interest.javaknowledge.Rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.adam.Interest.javaknowledge.Rmi.interfaces.HelloWorld;

/**
 * helloworld远程接口客户端
 */
public class Client {

	public static void main(String[] args) {
		//创建接口对象，但是此时我们就能用new了，而是用rmi的远程访问方法来得到这个接口对象的实例
		try {
			HelloWorld helloWorld = (HelloWorld) Naming.lookup("rmi://localhost:9999/Hello");//传入服务端发布的Url名
			
			//此时就可以调用helloworld的say方法了
			helloWorld.sayHello();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
}
