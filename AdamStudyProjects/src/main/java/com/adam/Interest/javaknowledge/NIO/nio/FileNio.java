package com.adam.Interest.javaknowledge.NIO.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * java nio 案例演示
 * @author adam
 * 2015.5.26
 * nio是java 1.4新推出的一项技术，其重点是为了更高效的让java程序请进行io操作，它主要是基于channel和buffer来进行流的读取和写出
 * 而传统的io则是基于stream流来进行操作的，并且传统io是阻塞io，意思就是如果此时io没有读完一行数据，则当前线程是会暂停的，但是nio不同，它是
 * 非阻塞Io，即使当前线程的io还在读数据，此线程依旧可以做其他事情 。
 * 传统io由于是流的形式，所以只能一次读取，一次使用（试想水流能无限用吗，当然不能了，因为只能流一次），而nio则不同，由于基于通道的io操作，所以我们
 * 完全可以通过标记缓冲区的位置，来重复使用数据，除非我们采取了通道释放等操作
 */
public class FileNio {

	//nio的操作方式
	@SuppressWarnings("resource")
	public static void readFile(String filepath){
		
		//首先定义通道,既然这里是处理文件，我们自然用fileChannel通道
		FileChannel channel = null;
		
		//由于nio中，并不能直接打开一个channel,所以我们需要通过Inputstream, outputstream, RandomAccessFile这三种对象来打开channel
		try {
			RandomAccessFile file = new RandomAccessFile(filepath, "rw");//rw表示允许对此文件进行读写操作
			//把文件转换为channel
			channel = file.getChannel();
			//前面说过，nio是基于channel和Buffer的，这里有channel，自然也要有Buffer
			ByteBuffer buffer = ByteBuffer.allocate(1024);//采用Nio的bytebuffer
			//循环读取
			while (channel.read(buffer) != -1) {
				//此时我们说过，nio是基于通道的，可以从中无限读写，那么此时我们必须要做一个标记，来表示此时的channel和Buffer究竟是读状态还是写状态
				//正好，buffer有个flip方法，就是切换标记的，此时就表示我们的Buffer切换为写状态,并且此时buffer会让指针重置到Buff区的区首位置，即让
				//buffer重新读取数据
				buffer.flip();
				//然后打印，我们先把buffer转换成byte数组，然后通过string对象打印出来，经观察，我们发现正好buffer有toarrays方法，是转换为byte数组的
				System.out.println(new String(buffer.array(), "gbk"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//nio写出文件
	@SuppressWarnings("resource")
	public static void weiteFile(String filePath){
		
		FileChannel channel = null;
		try {
			//还是需要通过inputstream outputstream 或者randomfile来得到channel
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");//后面rw表示允许对此文件进行读写操作
			//打开文件的通道
			channel = file.getChannel();
			//我们想向文件写入的数据
			String string = "我是nio,哈哈哈哈呵呵呵呵";
			//创建buffer
			ByteBuffer buffer = ByteBuffer.wrap(string.getBytes());//wrap表示我们把数据放入这个通道中
			//把buffer的内容通过通道写到文件中
			channel.write(buffer);
			System.out.println("====写出数据成功====");
			buffer.clear();//清空缓存
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("resource")
	public static void copyFile(String filePath, String outPath){
		FileChannel resourceFileChannel = null;
		FileChannel outFileChannel = null;
		try {
			RandomAccessFile resourceFile = new RandomAccessFile(filePath, "r");
			RandomAccessFile outFile = new RandomAccessFile(outPath, "rw");//由于没有单独的w操作，所以只有写rw
			
			resourceFileChannel = resourceFile.getChannel();
			outFileChannel = outFile.getChannel();
			
			//运用channel自带的复制方法，把filechannel通道中的数据放入outchannle通道中，同时写出到目标对象里，这里的目标对象就是我们的outfile
			outFileChannel.transferFrom(resourceFileChannel, 0, resourceFileChannel.size());
			System.out.println("=== 文件复制成功 ===");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				outFileChannel.close();
				resourceFileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
