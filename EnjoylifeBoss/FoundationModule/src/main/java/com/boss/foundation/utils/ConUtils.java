package com.boss.foundation.utils;

import java.util.*;

/**
 * 集合工具，方便直接调用得到集合，而不用map = new hashmap()....这种类似的长代码
 * @author adam
 *
 */
public class ConUtils {

	/**
	 * 得到hashmap
	 */
	public static <K, V> Map<K, V> hashmap(){
		return new HashMap<K, V>();
	}
	
	/**
	 * 得到arraylist
	 */
	public static <E> List<E> arraylist(){
		return new ArrayList<E>();
	}
	
	/**
	 * 得到hashset
	 */
	public static <E> Set<E> hashset(){
		return new HashSet<E>();
	}

	/**
	 * 验证集合是否为空
	 */
	public static <E> boolean isNotNull(Collection<E> collection){
		return collection != null && !collection.isEmpty();
	}

	public static <K, V> boolean isNotNull(Map<K, V> map){
		return map != null && !map.isEmpty();
	}
}
