package com.adam.Interest.javaknowledge.Rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.adam.Interest.javaknowledge.Rmi.interfaces.HelloWorld;
import com.adam.Interest.javaknowledge.Rmi.interfaces.impl.HelloWorldImpl;

/**
 * 我们说过，RMI是远程接口方法，既然是远程，那么就涉及到了网络，既然是网络，那么必然有服务端和客户端
 * 这就是一个简短的RMI服务端程序
 *
 */
public class HelloWorldServer {

	public static void main(String[] args) {
		try {
			//我们创建远程接口对象
			HelloWorld helloWorld = new HelloWorldImpl();
			//然后注册到我们的服务中，这里注册到本地，需要Locationregistery类
			LocateRegistry.createRegistry(9999);//端口定义为9999
			
			//接着把我们的远程接口对象注册到服务中，这里有两种方式，第一种是带协议rmi的注册，另一种是省略的注册，两者都可以
			Naming.bind("rmi://localhost:9999/Hello", helloWorld);
			//Naming.bind("//localhost:9999/Hello", helloWorld);
			//上面两种都可以，注意定义规则，域名后面的hello则表示此时我们把这个远程接口命名为Hello,则访问时，就不用helloworld了，而是方法Hello
			
			//打印一句话，表示远程服务开启
			System.out.println("远程接口服务开启------------------->>>");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
}
