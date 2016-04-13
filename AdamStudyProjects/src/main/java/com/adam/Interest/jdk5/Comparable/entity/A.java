package com.adam.Interest.jdk5.Comparable.entity;
/**
 * 用排序器来排序，这里要求，优先按年龄排序，如果年龄相同，则按num排序
 * 要使用排序器，必须实现排序器接口
 * @author adam
 * 2015.5.25
 *
 */
public class A implements Comparable<A> {

	private String name;
	
	private int age;
	
	private int num;

	@Override
	public int compareTo(A o) {//这就是排序方式设置,是排序器的唯一方法，我们要实现它的具体内容，这个方法返回一个Int数，供三种，-1，0，1，依次为大于，等于，小于
		//o为被比较对象，如果我们想比较当前对象与上一个对象的年龄，则可以用this关键字，即this.age > 0.age
		/*if(this.age > o.age){
			return 1;
		}else if(this.age < o.age){
			return -1;
		}else{//此时说明是年龄相等，则就比较name
			if(this.num > o.num){
				return 1;
			}else{
				return -1;
			}
		}*/
		
		/**
		 * 注意，这里的1，-1并不是单纯的表示大小，而是这个集合的排序方式
		 * 我们上面写了，如果this.age > o.age，返回1，则最后的结果是升序排列，即小的在前面，大的在后面
		 * 相反，如果我们写了this.age > o.age，返回-1，则最后结果是降序排列，即大的在前面，小的在后面
		 */
		
		//当然，下面的书写方式也是可以的，甚至会更加易懂和简洁
		if(this.age == o.age){
			return o.num - this.num;//如果是被排列的放在前面，即参数o代表的对象，则按照降序，如果是this代表的对象放在前面，则升序
		}else{
			return o.age - this.age;
		}
	}
	
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
