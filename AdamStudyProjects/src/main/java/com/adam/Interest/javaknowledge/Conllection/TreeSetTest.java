package com.adam.Interest.javaknowledge.Conllection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by wanxs on 2015/9/7.
 */
public class TreeSetTest {

    public static void main(String[] args){

        /**
         * Treeset集合，是java中比较难的集合了，它其实存数据的时候，和其他的比如hashset一样，
         * 但是它在存放数据的时候是有前提的，就是这个被存入的对象必须是能够排序的，因为treeset实际上是一个二叉树
         * 结构，所以要求必须要有序，为了让我们的对象能够有序，所以就要求存入treeset的元素必须实现comparable接口
         */
//        TreeSet<Long> set = new TreeSet<Long>();

//        set.add("sss");
//        set.add("aaa");
//        set.add("zzz");
//        set.add("aaa");

//        set.add(11l);
//        set.add(55l);
//        set.add(33l);
//        set.add(11l);

        /**
         * 到这里我们就发现，其实我们用的string和什么Integer,long，查看源代码就知道，它默认就实现了comparable接口
         * 所以我们就不用自己做了
         *
         * 接下来我们就做一个自定义对象来演示
         */

        //此时我们不实现comparable接口：
//        TreeSet<People> set = new TreeSet<People>();
//        set.add(new People("老张", 22));
//        set.add(new People("老王", 23));
//        set.add(new People("小周", 25));
//        set.add(new People("小周", 23));

        /**
         * 此时就抛出了一个经典的异常
         * com.adam.Interest.javaknowledge.Conllection.People cannot be cast to java.lang.Comparable
         * 说明people这个对象必须要实现comparable接口
         */


        /**
         * 除此之外，此时我们还可以自定义一个比较器
         * 见下面的peoplecomparable，这个比较器我们实现的是comparator接口
         * 并且比较方法我们只比较名字，不比较年龄
         */
        //此时我们给treeset同时传入一个我们自定义的比较器
        TreeSet<People> set = new TreeSet<People>(new PeopleComparable());
        set.add(new People("老张", 22));
        set.add(new People("老王", 23));
        set.add(new People("小李", 25));
        set.add(new People("小周", 23));
        //此时我们发现，顺序是按照我们的比较器的方式来排列的，这里直接就忽略了我们people中实现的comparable接口的比较顺序了
        //说明当treeset有比较器传入的时候，会优先考虑比较器的比较方法，而忽略原对象中的comparable接口的比较方法

        List<People> list = new ArrayList<People>();


        //由于是set集合，所以必须用迭代器来取, 这里用java8的方式来取
        set.stream().forEach((e) -> System.out.println(e.getName() + "    " + e.getAge()));


    }

}

class People implements Comparable<People>{

    private String name;
    private int age;

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(People o) {
        //compareTo传入的是被比较的对象，此时说明这个o对象就要和this当前对象比
        //这里我们用age来排序，用当前对象的age - o的age
        //然后返回这个差，当差为正数，说明当前this对象大，则放在后面，这时采用的就是升序排列
        int age = this.age - o.getAge();
        //想变成降序也很简单，用o.getAge - this.age即可
        //注意return时，还需要做一步判断，即如果age = 0， 说明此时this的age与o的age一样大了，那么此时我们就继续用name去比较
        //但是name由于是string, java已经帮我们做了comparato，所以我们就不用自己实现name的comparaeTo方法了
        return age == 0 ? name.compareTo(o.getName()) : age;


        //说明comparaeTo默认是采用升序来排列，即如果相减的值为正数，则被减数放在减数后面
        //如此时this.age 大于 o.age，则this就放在o的后面
    }
}

class PeopleComparable implements Comparator<People>{

    //自定义比较器就不是实现comparable接口了，而是实现comparator接口
    @Override
    public int compare(People o1, People o2) {
        //此时我们采取的是直接比名字
        return o1.getName().compareTo(o2.getName());
    }
}

