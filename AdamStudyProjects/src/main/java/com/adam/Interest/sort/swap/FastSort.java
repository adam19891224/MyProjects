package com.adam.Interest.sort.swap;

import com.adam.Interest.sort.ArraysIntObject;

/**
 *  快速排序------------交换排序
 *
 *	思想要点：
 *		每一次选择一个基数（通常为数组首位或者末位），然后进行一次遍历，分出比基数少的，比基数多的两个部分，然后继续循环左边部分
 *	    和右边部分，用同样的方式，分出此时左边部分的左边，基数和右边，右边部分的左边，基数和右边
 *	    然后继续对这些进行分，直到最后分为一个一个为单位的情况，当分成这个情况的时候，这个数组也就完成排序了
 *
 *	    这个大体思想我们可以用这样一个形象的例子来做 挖坑 填坑
 *	    设此时数组：
 *	    110， 33， 451， 22， 255
 *	    放入方法quick(arr, start, end)中，即：quick(arr, 0, arr.length - 1)
 *	    此时我们假设基数为arr[0] 即110， 并赋值为temp，那么此时arr[0]就相当于一个坑了，因为此时arr[0]被变量temp表示，arr[0]就没有实际意义了
 *	    然后此时数组就变成这样
 *      null, 33, 451, 22, 255
 *      然后向后比较，此时我们从后向前
 *      while(start < end && temp < arr[end]){
 *
 *          此时执行end--
 *          end--;
 *      }
 *      当上面循环执行跳出之后，说明此时arr[end]小于了temp，则把这个arr[end]的值放入arr[0]这个坑中
 *      根据上面的数组，又后向前遍历时，22满足小于110, 即end = 3, 此时就把arr[0]赋值为22，然后此时arr[3]的数字就没有意义了，就成为了一个新的坑
 *      22, 33， 451， null, 255
 *
 *      //此时我们发现数组的后半部分有一个坑，然后就重新从前面开始遍历，直到找到一个比基数大的数，填入后面的坑
 *      此时又从前面开始向后遍历，：
 *      whiel(start < end && temp > arr[start]){
 *
 *          start++
 *
 *      }
 *      当跳出循环时，说明此时arr[start]的值大于了基数，就把这个值赋值到上面的arr[end]这个坑中
 *      根据数组，我们发现451不满足110 > 451，则此时start = 2,然后把arr[3]位置放置这个451, 同理，此时arr[2]就没有意义了，成立了新坑
 *      22, 33, null, 451, 255
 *
 *      然后此时又把这个坑赋值给110这个基数，。即arr[start] 即arr[2]
 *      此时数组：
 *      22， 33， 110， 451， 255， start为2
 *      然后把这个2返回
 *
 *      此时就分成了22， 33， arr[start] 451, 255， 此时就可以确定start这个位置必然是这个基数的位置，这里就相当于经过一次
 *      循环后，把基数确定了顺序
 *      然后继续，此时就把左边的22, 33再次进行上面的循环quick(arr, 0, start - 1)，同时把451, 255也再洗进行上面的循环quick(arr, start + 1, end)，这样直到最后，整个数组就排序好了
 *
 *
 *      其实我们发现，快速排序就是用分治法的思想，将一个数组一分再分，最后分好后，数组也就排好序了
 *      所以这里就涉及到递归调用分数组的方法，即把当前数组分为左边，基数，右边的方法
 *      但是我们要注意步骤，确定基数后，先从后面找一个比基数小的放在前面，此时基数是arr[0]
 *      然后从数组前面找一个比基数大的，放在后面
 *
 *	前提：
 *		无
 *
 *
 */
public class FastSort {

    public static void main(String[] args){

        int[] arr = ArraysIntObject.getObject();

        sort(arr, 0, arr.length - 1);

        ArraysIntObject.show(arr);

    }

    public static void sort(int[]arr, int start, int end){
        if(start < end){
            //调用分数组的方法，将数组分为基数，基数左边（全部小于基数）， 基数右边（全部大于基数）的方法， 并返回当前基数的下标
            int tempIndex = splitArrToLeftTempRight(arr, start, end);
            //得到此时根据start和end所得到的基数的下标
            //然后根据下标，继续递归调用sort，执行0 到 tempIndex - 1部分和tempIndex + 1到end部分
            sort(arr, start, tempIndex - 1);
            sort(arr, tempIndex + 1, end);
        }
    }

    //此时定义一个方法，来分 arr为目标数组，start表示起始位置，end表示结束位置
    //我们说过，我们就把起始位置或者结束位置的数作为基数，这里就以起始位置为主
    public static int splitArrToLeftTempRight(int[] arr, int start, int end){
        int temp = arr[start];//设置基数，此时arr[start]就是一个坑了
        //执行循环, 即把所有比基数小的放在基数左边，比基数大的放在基数右边
        while(start < end){
            //然后判断，根据上面的填坑机制, 我们先从后面开始
            while(start < end && temp < arr[end]){
                //如果满足，则说明此时arr[end]比基数大，就不变，然后--
                end --;
            }
            //当跳出循环时，说明此时temp >= arr[end] 则填入arr[start]这个坑中
            arr[start] = arr[end];
            //此时arr[end]就成为了一个新坑

            //然后根据上面的填坑机制，当从后面开始，填入一个坑到前面后，需要从前面开始，填入一个比基数大的数到后面的坑
            while(start < end && temp > arr[start]){
                //进入方法，说明此时temp 大于 arr[start]
                //则让start 自加1
                start ++;
            }
            //当跳出循环后，说明此时temp < arr[start] 则把这个数填入后半部分 的arr[end]这个坑中
            arr[end] = arr[start];
            //此时就确定了一组数，一个比基数小，放在基数的左边，一个比基数大，放在基数的右边
        }
        //执行完循环之后，此时就满足比基数小的数字全部在左边，比基数大的数字全部在右边了
        //此时arr[start]成为了一个新坑，然后就把这个坑放入基数
        arr[start] = temp;
        //此时返回这个start, 说明一次排列后，start就为基数的下标，此时基数位置已经确定，同时继续排列基数左边和右边部分
        //即0 到 start - 1   和    start + 1到数组末尾end
        return start;
    }


}
