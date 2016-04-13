package com.adam.Interest.sort.swap;

import com.adam.Interest.sort.ArraysIntObject;

/**
 *  冒泡排序------------交换排序
 *
 *	思想要点：
 *		对于还没有排列的数组，让数组末尾的数依次向上做比较，即两两相邻比较，只要arr[a + 1] < arr[a], 就让它们两个
 *	    相互交换，这样的话，每一次循环完毕，arr[arr.length]的数必然是最大数
 *
 *	前提：
 *		无
 *
 *
 */
public class BubbleSort {

    public static void main(String[] args){

        int[] arr = ArraysIntObject.getObject();
        //第一个循环，定义循环次数，即此时数组长为arr.length, 那么总共就需要比较arr.length - 1次
        for(int a = 0; a < arr.length - 1; a++){

            //此时开始冒泡比较，每次比较后，数组末尾的必然就是当前的最大数，所以下一次就需要去掉末尾的数
            for(int b = 0; b < arr.length - 1 - a; b++){
                if(arr[b] > arr[b + 1]){
                    //如果arr[b]大于arr[b + 1] 则让arr[b]与下一个，即arr[b + 1]交换位置
                    ArraysIntObject.swap(arr, b, b + 1);
                    //当第二次循环时，这次的arr[b + 1]就变成了下一次的arr[b]
                }
            }
        }

        ArraysIntObject.show(arr);
    }

}
