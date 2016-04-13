package com.adam.Interest.sort.swap;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Created by wanxs on 2015/9/6.
 */
@RunWith(Theories.class)
public class FastSortTest {

//    @DataPoint
//    public static String nameValue1 = "老张";
//
//    @DataPoint
//    public static String nameValue2 = "老王";
//
//    @DataPoint
//    public static int ageValue1 = 10;
//
//    @DataPoint
//    public static int ageValue2 = 20;

    @DataPoints
    public static String[] names = {"老李", "小周"};

    @DataPoints
    public static int[] ages = {20, 30, 50};

    @Theory
    public void testTheory(String name, int value){
        System.out.println("名字：" + name + "   年龄：" + value);
    }

    private int[] arrs;

//    @BeforeClass
//    public static void beforeClass(){
//        System.out.println("beforeClass执行");
//    }

//    @Before
//    public void setUp() throws Exception {
//        System.out.println("执行setUp");
//        arrs = ArraysIntObject.getObject();
//    }
//
//    @Test
//    public void testSplitArrToLeftTempRight() throws Exception {
//        FastSort.splitArrToLeftTempRight(arrs, 0, arrs.length - 1);
//
//    }
//
//    @Test
//    public void testSort() throws Exception{
////        FastSort.sort(arrs, 0, arrs.length - 1);
//        int[] arr = ArraysIntObject.getObject();
//        Assert.assertArrayEquals(arr, arrs);
//    }

//
//    @Test(timeout = 1000)
//    public void testTimeOut() throws InterruptedException {
//        Thread.sleep(5000);
//        System.out.println("ss");
//    }
//
//    @Rule
//    public ExpectedException exception = ExpectedException.none();
//
//    @Test
//    public void testException() throws ArrayIndexOutOfBoundsException{
//
//        exception.expect(ArrayIndexOutOfBoundsException.class);
//        int[] arr = new int[3];
//        int b = arr[1];
//    }



//    @After
//    public void tear(){
//        System.out.println("执行tear方法");
//    }
//
//
//    @AfterClass
//    public static void afterClass(){
//        System.out.println("afterClass执行");
//    }

}