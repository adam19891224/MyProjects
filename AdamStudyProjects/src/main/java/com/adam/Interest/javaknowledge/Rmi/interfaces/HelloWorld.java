package com.adam.Interest.javaknowledge.Rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI案例举例，RMI即远程接口调用，它的作用是运行一个jvm内的java程序调用另一个jvm内部的java程序，看起来有点想web和webservice的应用，但是这种方式更加原始和底层花
 * 注意事项：如果我们要创建一个远程接口，则必须继承remote接口,同时必须抛出 RemoteException 这个异常
 * 
 * @author adam
 * 2015.5.22
 */
public interface HelloWorld extends Remote {

	//我们的sayHello方法
	public void sayHello() throws RemoteException;
	
}
