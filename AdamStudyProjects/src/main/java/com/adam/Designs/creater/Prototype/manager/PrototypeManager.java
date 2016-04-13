package com.adam.Designs.creater.Prototype.manager;

import java.util.HashMap;
import java.util.Map;

import com.adam.Designs.creater.Prototype.entity.Prototype;

/**
 * 此时我们可以编写一个原型的管理类来管理各个原型，实质就是利用一个map来管理
 * @author adam
 *
 */
public class PrototypeManager {

	//定义一个map
	private static Map<String, Prototype> map = new HashMap<String, Prototype>();
	
	//无参构造
	public PrototypeManager(){
		
	}
	
	//向map传值
	public void setPrototypeObject(String proName, Prototype entity){
		map.put(proName, entity);
	}
	
	//得到map的值
	public Prototype getPrototypeObject(String proName) throws Exception{
		Prototype pro = map.get(proName);
		if(pro == null){
			throw new Exception("对不起，你找的" + proName + "对象不存在");
		}
		return pro;
	}
	
	//销毁
	public void destoryPrototypeObject(String proName){
		map.remove(proName);
	}
}
