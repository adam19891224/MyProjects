package com.adam.Test.TestObjUtils;

public class Main {

	
	public static void main(String[] args) {
		//测试对象工具的map方法
		
//		Map<String, Integer> map = Utils.getMap();
//		map.put("1", 2);
//		
//		Map<Integer, Float> map2 = Utils.getMap();
//		map2.put(1, 1.1f);
//		
//		System.out.println(map);
//		System.out.println(map2);
//		
//		
//		Date date = new Date();
//		
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		System.out.println(date);
//		
//		calendar.add(Calendar.MINUTE, 30);
//		
//		Date date1 = calendar.getTime();
//		System.out.println(date1.after(date));
		
//		System.out.println(Integer.highestOneBit(99 << 1));
//		System.out.println(Integer.toBinaryString(50));
//		System.out.println(Integer.toBinaryString(500));
//		System.out.println(Integer.toBinaryString(500 >>> 16));
//		System.out.println(Integer.toBinaryString(500 | 500 >>> 1));
		
//		System.out.println(Integer.toBinaryString(50 >>> 1));
		//1100011
		
//		Map<String, String> map1 = new HashMap<String, String>();
//		map1.put("1", "1");
//		map1.put("2", "1");
//		map1.put("3", "1");
//		
//		Map<String, String> map2 = new HashMap<String, String>();
//		map2.putAll(map1);
		
//		System.out.println(1 << 30);
		
		new Adapter(){
			
			@Override
			public void run() {
				super.run();
				show();
				System.out.println("sssss");
			}
		}.start();
		
		
		
		
	}
}

interface MyRunable{
	public void run();
	
	public void show();
}

class Adapter extends Thread implements MyRunable{

	@Override
	public void show() {
		System.out.println("你好");
	}
	
}
