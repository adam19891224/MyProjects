package com.adam.Designs.behaviour.Iterator.iterator;
/**
 * 迭代器模式
 * 当我们需要遍历一个聚合集合时，甚至不论这个集合是什么类型的，我们只想要遍历它，此时就可以采用迭代器模式来遍历，迭代器提供了包括得到第一个元素，得到下一个元素，得到最后个元素和得到当前元素
 * 等通用的迭代遍历方法
 * @author adam
 * 2015.5.27
 */
public interface Iterator {
	
	//这就是我们的迭代器，首先创建迭代器默认的几个方法
	public Object frist();
	
	public Object last();
	
	public boolean isNotDone();
	
	public Object getCurrent() throws Exception;

}
