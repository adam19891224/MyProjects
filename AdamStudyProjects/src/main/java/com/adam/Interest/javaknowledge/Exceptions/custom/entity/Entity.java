package com.adam.Interest.javaknowledge.Exceptions.custom.entity;

import com.adam.Interest.javaknowledge.Exceptions.custom.exception.checked.CustomIoException;
import com.adam.Interest.javaknowledge.Exceptions.custom.exception.unchecked.CustomRuntimeException;

/**
 * 自定义异常，这里定义两个方法，一个抛出我们定义的customRuntime， 另一个抛出我们定义的customIo
 * @author adam
 *
 */
public class Entity {

	public void throwCustomRuntime() throws CustomRuntimeException{
		
		System.out.println("ssss");
		throw new CustomRuntimeException("抛出了自定义的runtime异常");
	}
	
	public void throwCustomIo() throws CustomIoException{
		System.out.println("ssss");
		throw new CustomIoException("抛出了自定义的io异常");
	}
	
}
