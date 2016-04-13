package com.adam.Designs.behaviour.Visitor.interfaces;

import com.adam.Designs.behaviour.Visitor.interfaces.impl.MyElement;
import com.adam.Designs.behaviour.Visitor.interfaces.impl.YourElement;


/**
 * 访问者
 * @author adam
 * 2015.8.21
 */
public interface Visitor {

	//通常访问者会有一个或者多个访问方法, 这里我们就提供一个方法方法，访问的是Element接口类型的子元素，这里访问myelement
	public void visit(MyElement e);
	
	//利用重载, 这里访问的是yourelement元素
	public void visit(YourElement e);
	
}
