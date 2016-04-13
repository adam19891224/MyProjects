package com.adam.Designs.structure.FlyWeightCase.factory;

import java.util.Map;

import com.adam.Designs.structure.FlyWeightCase.entity.Entity;
import com.adam.Utils.Utils;

/**
 * 享元模式工厂
 *
 */
public class Factory {

	 private static Map<String, Object> map = Utils.getMap();
	 
	 public Entity getObj(Entity entity){
		 if(map.containsKey(entity.getS())){
			 return (Entity) map.get(entity.getS());
		 }else{
			 map.put(entity.getS(), entity);
			 return (Entity) map.get(entity.getS());
		 }
	 }
	
	 public void checkMap(){
		 for(Map.Entry<String, Object> entry : map.entrySet()){
			 System.out.println("key   :" + entry.getKey());
			 System.out.println("val   :" + entry.getValue());
			 System.out.println("================================================");
		 }
	 }
	 
}
