package com.adam.Interest.sort.choice;

import com.adam.Interest.sort.ArraysIntObject;

/**
 * 直接选择排序------选择排序
 *
 *	思想要点：
 *  选择一个数组，与目标数组中第n个交换	， 即选择第一个最小数数与数组的0号位的数字交换，然后在剩下的数中找寻最小数，即实际第二小
 *  的数，与数组的第二个数交换，这样一直知道倒数第二个数交完完后，最后一个数不用交换，自然就是最大的了
 *  并且每次选择数时，它之前的所有数字必然是一个有序的
 *
 *	前提：
 *	无
 *
 */
public class DriectChoiceSort  {

    public static void main(String[] args){
        int[] arr = ArraysIntObject.getObject();

        for(int a = 0; a < arr.length - 1; a++){
            //选择次数为长度 - 1, 最后一次不需要做了
            //假设此时arr[a]就为最小数，我们把它放入一个变量中
            int min = arr[a];
            int minIndex = a;//同时把下标放入一个变量中

            //然后让min与a之后的所有元素比较, 用来确定min是否就是整个数组中的最小数
            for(int b = a + 1; b < arr.length; b++){
                if(arr[b] < min){//此时说明a后面的数组中有数字比我们选择的min要小，就把min变成arr[b], 同时下标改为b
                    min = arr[b];
                    minIndex = b;
                }
            }
            //当循环完一次for b的循环后，此时min必然是整个数组中当前次数的最小数，minIndex必然是这个数的下标
            //按照我们的需求，此时就交换min与arr[a]的位置
            //我们知道，由于min是最小值，minIndex是最小值坐标，也就意味着如果我们要交换
            //就需要把arr[a]的值放到arr[minIndex]上，然后让arr[a]等于最小值min
            //即加上此时a为1， arr[1]为15，min为4， minIndex为18（minIndex是a之后的元素，所以永远比a大）
            //此时我们就要把arr[1]与arr[18]交换，所以arr[18]就需要等于arr[1]，然后由于此时arr[18]已经更换
            //所以只能用arr[1] = min这个变量来做
            arr[minIndex] = arr[a];
            arr[a] = min;

        }

        //打印结果
        ArraysIntObject.show(arr);
    }

    /**
     * 这就是直接选择排序，思想就是加载当前元素， 即arr[a]为最小的，然后与它后面的比较，因为前面的在之前就排好了
     * 然后比较后，如果还有小于arr[a]的，就交换，这样每一次循环，都能保证当前数组下标a放置的必定时这一轮的最小数
     */
}
