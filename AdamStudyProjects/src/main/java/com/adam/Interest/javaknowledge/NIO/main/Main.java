package com.adam.Interest.javaknowledge.NIO.main;

import com.adam.Interest.javaknowledge.NIO.nio.FileNio;

/**
 * io与nio测试
 * @author adam
 *
 */
public class Main {

	public static void main(String[] args) {
		//io方式读取，这里统一读取F盘下generator.xml文件
//		String filePath = "f://generator.xml";
//		FileIo.readFile(filePath);
		
		//nio方式
//		FileNio.readFile(filePath);
		
		//io 写出数据到文件
//		FileIo.weiteFile("f://fileio.txt");
		
		//nio 写出数据到文件
//		FileNio.weiteFile("f://filenio.txt");
		
		//io方式复制文件
//		FileIo.copyFile("f://addInit.action", "f://fileio.txt");
		
		//nio方式复制文件
		FileNio.copyFile("f://addInit.action", "f://filenio.txt");
	}
	
}
