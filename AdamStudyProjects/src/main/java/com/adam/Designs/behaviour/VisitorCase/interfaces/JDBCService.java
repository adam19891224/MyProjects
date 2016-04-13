package com.adam.Designs.behaviour.VisitorCase.interfaces;

import java.util.List;

/**
 * 访问模式联系
 * 这里模拟一个spring mvc的过程，controller作为访问者，去调用service的select方法
 * 然后根据不同的需求修改方法即可
 * 
 * @author adam
 * 2015.8.21
 */
public interface JDBCService {

	List<String> select();
	
	//创建访问者接收方法
	void accept(Controller c);
}
