package com.adam.Designs.behaviour.State.state;
/**
 * 状态模式
 * 这种模式适用于实体类的状态由外部定义的情况，此时实体类的某项操作要取决于它的状态，就可以用这种设计模式
 * 例如，水有三种状态，气态，固态和液态，那么此时我们就可以根据不同的状态，来操作不同的水实体
 * @author adam
 * 2015.6.3
 */
public interface State {

	//展示状态的方法
	public void show();
	
}
