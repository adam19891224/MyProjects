package com.adam.Test.TestBuilderConstractor.interfaces;
/**
 * 此时我们创建一个Builder接口，然后利用泛型来创建实现它的Builder实现类
 * @author adam
 *
 */
public interface Builder <T> {
	public T create();

}
