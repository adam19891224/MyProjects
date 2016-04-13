package com.adam.Interest.javaknowledge.Rmi.interfaces.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.adam.Interest.javaknowledge.Rmi.interfaces.HelloWorld;
/**
 * helloworld的实现类，从这里我们看出，我们在实现自定义的远程接口之外，还需要继承衣蛾unicastremoteobject的父类
 *
 */
public class HelloWorldImpl extends UnicastRemoteObject implements HelloWorld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4075981630707813799L;

	public HelloWorldImpl() throws RemoteException {
	}

	@Override
	public void sayHello() throws RemoteException {
		System.out.println("你好，世界");
	}

}
