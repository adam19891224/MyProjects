package com.nio.randomaccessfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Adam
 * 2016/9/2
 */
public class Demo1 {

    private static final String TXT_PATH = "d:/test1.txt";

    private static final String PNG_PATH = "d:/test.png";

    public static void main(String[] args){

        Demo1 demo1 = new Demo1();
        demo1.testMarkPoint(30);
//        demo1.testAppendFile("\r\n低头思故乡");
//        demo1.copyFile1(15);
//        demo1.copyFile2(15);
    }


    /**
     * 测试randomaccessfile对象的标记位置方法
     * random文件可以支持我们任意标记位置，然后从位置开始读取
     * 构造函数总共有两个，一个是String path, String mode  另一个是File file, String mode
     *  mode 参数支持值
     *      r 代表以只读方式打开指定文件
 *          rw 以读写方式打开指定文件
     *      rws 读写方式打开，并对内容或元数据都同步写入底层存储设备
     *      rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
     *
     *
     * 特别注意一点：
     *  如果没有读取到文件，randomaccessfile不会为空，而回直接抛出filenotfound异常
     *
     * @param point
     */
    public void testMarkPoint(long point){

        RandomAccessFile file;
        try {

            file = new RandomAccessFile(TXT_PATH, "r");
            //通过getFilePointer获取当前的标记
            long nowPoint = file.getFilePointer();
            System.out.println("第一次读取文件后，获取的标记是： " + nowPoint);

            //通过seek方法设置新的标记点
            file.seek(point);
            System.out.println("设置此时文件的标记点为： " + point);
            //然后开始从标记位置读取文件
            byte bt[] = new byte[1024];
            int result = 0;
            while ((result = file.read(bt)) != -1){
                //输出读取的字符串
                System.out.println(new String(bt, "utf-8"));
            }

            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向文件末尾追加字符串，此时我们可以采用传统的io把文件读进内存，然后再到末尾拼接我们需要的，最后写回
     * 到文件中，但是这太麻烦，我们可以直接用random文件对象的随机方法方法，直接把文件指针指向文件的末尾
     * 然后开始进行写字符
     * 注意，我们由于要进行写操作，所以文件的mode就不能用r了，而是rw
     * @param str
     */
    public void testAppendFile(String str){
        RandomAccessFile file;
        try {

            file = new RandomAccessFile(TXT_PATH, "rw");

            //通过seek方法设置新的标记点 此时点为文件的最后一个位置
            file.seek(file.length());
            //然后让对象从文件末尾开始写下一句话
            file.write(str.getBytes("utf-8"));
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 接下来做一个简单的端点续传例子，这里我们模拟一个操作，把D盘的文件复制到E盘中，分为两步，第一步我们只复制文件长度的一半到E
     * 盘，第二步就复制另一半
     *
     */

    public void copyFile1(long tar){

        try {
            RandomAccessFile copy = new RandomAccessFile(PNG_PATH, "r");

            long length = copy.length();

            if(tar > length){
                return;
            }

            byte b[] = new byte[(int) tar];

            int res = 0;
            copy.read(b);

            RandomAccessFile target = new RandomAccessFile("e:/test.png", "rw");
            target.write(b);

            target.close();
            copy.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile2(long tar){

        try {
            RandomAccessFile copy = new RandomAccessFile(PNG_PATH, "r");
            File file = new File("e:/test.png");
            RandomAccessFile target = new RandomAccessFile("e:/test.png", "rw");

            //设置文件指针从tar开始
            copy.seek(tar);
            target.seek(tar);
            byte b[] = new byte[(int) tar];

            int res = 0;
            while ((res = copy.read(b)) != -1){
                target.write(b);
            }
            target.close();
            copy.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
