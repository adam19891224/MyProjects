package com.adam.Interest.javaknowledge.NIO.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 基于传统的io来操作文件
 * @author adam
 *
 */
public class FileIo {

	public static void readFile(String filePath){
		File file = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			//定义Byte数组读取
			byte[] b = new byte[1024];
			while (fis.read(b) != -1) {
				System.out.println(new String(b, "gbk"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void weiteFile(String filePath){
		String string = "呵呵哈哈呵呵哈哈";
		File file = new File(filePath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			//将我们的字符串转换为byte数组
			byte[] b = string.getBytes();
			//把数据输出到文件中
			fos.write(b);
			System.out.println("====写出成功====");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void copyFile(String filePath, String outPath){
		File resourcefile = new File(filePath);
		File outFile = new File(outPath);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(resourcefile);
			fos = new FileOutputStream(outFile);
			byte[] b = new byte[1024];
			while(fis.read(b) != -1){
				//将byte数组的数据写入文件
				fos.write(b);
			}
			System.out.println("=== 文件复制完毕 ===");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
