package com.adam.Interest.sort.choice;

import com.adam.Interest.sort.ArraysIntObject;

/**
 * 堆排序------选择排序
 *
 *	思想要点：
 *  根据堆在数据结构中的定义，即父节点永远比它的任意一个子节点小（小顶堆）或者父节点永远比它的任意子节点大（大顶堆）
 *  并且是一个完全二叉树（即除了第n层之外，其他层都能达到最大子节点， 由于二叉树，所以最大子节点为2）
 *  而第n层的节点要么是完全子节点（即有左有右），要么就只有左节点，如下
 *                  1
 *            2          3
 *         4      5   6     7
 *      7    8  9
 *      这就是完全二叉树，最后一层之前的所有节点都是最大子节点
 *      并且最后一层的节点要么是最大子节点（7  8），要么只在左节点(9)
 *
 *                    1
 *             2             3
 *         4      5       6     7
 *      7    8  ?   9
 *      这就不是完全二叉树，因为虽然最后一层之前的所有节点都是最大子节点
 *      但是最后一层的9，在右节点了，而应该在?处才是，所以这就不是完全二叉树
 *
 *   由此我们发现，完全二叉树从根节点开始到最后，是一个有序的树，所以我们就可以从跟节点开始取，当取出一个之后，剩下的继续排列为
 *   一个完全二叉树（即跟节点要么是最大的，要么是最小的），这样每取一个，都能保证这个数是剩下的所有数中的最大或者最小数，然后把这个
 *   数放入一个空数组中，当我们把树的所有节点取完后，这个数组自然就是一个有序的数组了
 *
 *	前提：
 *  需要做两步，
 *  第一步是把数组构成一个堆，即完全二叉树
 *  第二步是然根节点
 *  注意，取完后，需要用最后一层的最左边的节点补上，那么此时这个数必然不是完全二叉树了，所以还需要重新调整，调整完后继续取
 *  即需要重新调用构成堆的方法
 *
 *  所以综上，堆排序主要就是两个方法
 *  1.构成堆
 *  2.取根节点
 *  然后无限循环，直至最后一个节点取完，这个数组就构成完毕，并且必然是一个有序数组
 *
 *
 */

public class HeapSort {

    public static void main(String[] args){

        int[] arr = ArraysIntObject.getObject();

        for(int a = 0; a < arr.length - 1; a++){//循环长度减一次
            //构建堆，我们构建一个大顶堆来做，这样每次取出的根元素就是当前数组中的最大值
            createHeap(arr, arr.length - 1 - a);
            //交换元素, 当一次构建完后，此时跟元素必然是最大的，交换到当前循环的末尾
            //即如果当前数组是10，就交换到9，如果当前数组长度是9，就交换到8
            ArraysIntObject.swap(arr, 0, arr.length - 1 - a);
        }
        //当整个数组都遍历完后，此时数组序也就排好了
        ArraysIntObject.show(arr);

    }

    //构成堆的方法,数据结构中定义，数组与堆的关系如下
    //数组下标为n的元素，它的父节点为(n - 1) / 2
    //它的左子节点为2 * n + 1,  它的右子节点为2 * n + 2
    //所以我们就可以根据这个规则来判断，传入的是原来的待排序数组和此时数组的最大长度，当2 * n + 1或者2 * n + 2超过了数组的长度时
    //说明此时n下标的点在堆中就没有孩子节点了
    //注意的是，由于我们直接在原数组上操作，所以我们可以保证每次完成后，数组的末尾都是当前最大的值，所以
    //剩余的数组长度需要我们动态制定
    //如：
    //此时数组长度为10，经过一次排序后，arr[9]必然为最大的，那么此时剩余数组的最后一个节点就为8了，而不是9
    //同理，再次拍完后，此时arr[8]必然是第二大的数，那么此时数组剩余的数最后一个节点就是7了
    public static void createHeap(int[] arr, int lastIndex){

        //此时我们就从lastIndex的父节点开始做逆向循环，当然要大于等于0
        for(int a = (lastIndex - 1) / 2; a >= 0; a--){

            //此时定义一个临时变量来保存这个下标为a的元素
            int now = a;
            //然后判断这个下标为a的元素是否有子节点存在, 由于很有可能这个now的子节点还有子节点，所以用一个while循环来做
            while(2 * now + 1 <= lastIndex){
                //如果存在，这把这个节点又存入一个变量中，此时取名为nowSun,即当前元素的左子节点
                int now_sun = 2 * now + 1;

                //由于某一个元素的左子节点为2 * n + 1, 右子节点为n * 2 + 2, 而且这里我们定义了左子节点的变量
                //所以实际判断当前now元素的右子节点时，就只需要判断now_sun是否小于最后一个元素的下标即可
                //因为如果now_sun小于了最后一个元素下标，则至少说明now_sun + 1是等于最后一个元素的下标的，所以此时
                //就代表Now元素有右子节点
                //判断Now是否有右子节点
                if(now_sun < lastIndex){
                    //说明此时有右子节点
                    if(arr[now_sun] < arr[now_sun + 1]){
                        //然后判断当前now元素的左右子节点哪个大，如果右节点大，就把now_sun指向右子节点
                        now_sun++;
                        //即now_sun始终表示的值是左右子节点中较大的节点的下标
                    }
                }

                //如果此时now元素的值小于了左右子节点中的较大节点，即now_sun代表的节点，就交换这两个位置
                if(arr[now] < arr[now_sun]){
                    ArraysIntObject.swap(arr, now, now_sun);
                    now = now_sun;
                }else{
                    break;//如果不是，则说明此时Now元素就大于了它的左右子节点，则此时就正好是一个堆
                }
            }
        }
    }

}
