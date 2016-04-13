package com.adam.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象工具，作为常用的工具对象使用
 * @author adam
 * 2015.5.5
 *
 */
public class Utils {

	/**
	 * 通常我们在使用类似map的时候，会作如下的声明：
	 * map<String, Object> map = new HashMap<String, Object>();
	 * 这种声明就十分繁杂，我们可以通过对象工具来简化操作，在该对象工具中，我们用静态工厂方法的模式，创建一个对象，
	 * 那么当我们使用的时候，就可以如下方般类似的使用
	 * map<String, Object> map = ObjUtils.getMap();
	 */
	public static <K, V> Map<K, V> getMap(){
		return new HashMap<K, V>();
	}
	
	public static <E> List<E> getList(){
		return new ArrayList<E>();
	}
	
}
