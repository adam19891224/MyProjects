package com.adam.Interest.sort;

import java.util.Arrays;

public class ArraysIntObject {

	public static int[] getObject(){
		return new int[]{213, 2132, 121, 123, 434, 34534,
						 213123, 12131, 1125, 7676, 4567,
						 685685, 345345,678456, 2342323, 4545};
	}
	
	public static void swap(int[] arr, int left, int right){
		arr[left] = arr[left] ^ arr[right];
		arr[right] = arr[left] ^ arr[right];
		arr[left] = arr[left] ^ arr[right];
	}
	
	public static void show(int[] arr){
		System.out.println(Arrays.toString(arr));
	}
}
