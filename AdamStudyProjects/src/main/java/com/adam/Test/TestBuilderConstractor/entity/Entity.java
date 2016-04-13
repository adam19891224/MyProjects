package com.adam.Test.TestBuilderConstractor.entity;
/**
 * 通过构建起来创建对象
 * 我们常用的创建对象的方式，大致分为两种，一种是利用构造方法来创建对象，
 * 如下方的例子,如果我们希望在创建对象的时候，就给name和age附上初始值，那么我们采用的第一种方法就是构造方法创建，即我们提供一个带参数的构造方法：
 * public Entity(String name, String age){
 * 		this.name = name;
 * 		this.age = age;
 * }
 * 
 * Entity entity = new Entity("name", "age");
 * 此方式毫无疑问是没有问题的，但是如果我们此时类中的变量有5个，而且有时候我们并不希望全部都附上初始值，这个时候又怎么办呢？总不能写5的排列组合中构造方法吧
 * 
 * 
 * 此时我们就可以通过第二种方式来创建，即javabean模式，我们通过给该类的属性提供set,get方法，然后利用无参构造方法new一个对象，并通过提供的set方法进行赋值，如下
 * public void setName(String name){
 *		this.name = name;
 * }
 * public void setAge(String age){
 *		this.age = age;
 * }
 * Entity entity = new Entity();
 * entity.setName("name");
 * entity.setAge("age");
 * 毫无疑问，这样也是没有问题的，但是这样就会出现一个问题，我们在创建对象的时候，多次调用了该类的内部方法（set。。。），那么这样我们就不能保证该对象的同步性了，你或许会说
 * 在我们使用的ssh框架项目中，我们也没有做什么同步控制，一样没问题，这是因为ssh框架都对同步进行了良好的封装所导致的
 * 
 * 
 * 为了完美解决该问题，我们就提出了构建器来创造对象
 * 
 * @author adam
 *
 */
public class Entity {
	
	private final String name;
	private final String age;
	
	//定义一个内部类,在其内部定义两个同样的变量，与entity的变量相对应
	public static class Builder implements com.adam.Test.TestBuilderConstractor.interfaces.Builder<Entity>{
		private String bname;
		private String bage;
		
		public Builder(){//无参构造
			
		}
		
		public Builder name(String name){
			this.bname = name;//通过builder的内部方法来给Builder的属性赋值
			return this;//返回这个builder对象
		}
		
		public Builder age(String age){//同上面
			this.bage = age;
			return this;
		}

		public Entity create() {
			//此时创建一个create方法，返回的是这个外部类的实例，即Entity类的实例,同时把这个内部类Builder的实例传进去,但是要注意的是，我们必须提供一个Entity的构造方法，并且
			//该构造方法要传入一个Builder的实体
			return new Entity(this);
		}
		
		
	}
	
	//Entity的构造方法
	private Entity(Builder builder){
		//通过构建器对象来给entity赋值
		this.name = builder.bname;
		this.age = builder.bage;
	}
	
	//重写toString
	@Override
	public String toString() {
		return this.age + "     " + this.name;
	}
}
