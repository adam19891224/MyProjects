package com.adam.Interest.netty.demo.server;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * netty框架学习一 服务端
 * 
 * @author adam 2015.8.28
 */
public class ServerDemo {
	
	private static Map<String, Channel> map = new HashMap<String, Channel>();
	
	public static void main(String[] args) {
		new ServerDemo().startNettyServer();
	}

	@SuppressWarnings({ "resource", "unused" })
	public void startNettyServer() {

		// 创建两个group，一个作为服务器端接收客户端的处理信息，另一个作为返回给客户端的处理信息
		// 追踪源码发现，其实group就是继承了concurrent的ScheduledExecutorService线程池
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			// 首先创建serverbootstrap
			ServerBootstrap bootstrap = new ServerBootstrap();
			// 将上面的两个group传给bootstrap
			bootstrap.group(boosGroup, workerGroup);

			// 给bootstrap设置channel与channel处理器, 注意这个处理器需要我们自己定义
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ServiceHandler());// 此时就创建我们下面定义的处理器

			// 然后创建通道监听来监听bootstrap绑定的端口, 这里绑定9999端口
			ChannelFuture future = bootstrap.bind(8090).sync();
//			// 监听服务器关闭端口
//			future.channel().closeFuture().sync();
			Scanner s = new Scanner(System.in);
			
			for (;;) {
				System.out.println("请输入");
                String line = s.nextLine();
                Channel channel = map.get("1001");
                if(channel == null){
    				System.out.println("还没有客户端连接");
    				continue;
    			}
                if (line == null) {
                    continue;
                }
                /*
                 * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
                 * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
                 * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
                 * */
                //通过channle发送数据
                channel.writeAndFlush(line + "\r\n");
            }
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			//关闭group
			boosGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
	
	/**
	 * 自定义处理器, 我们创建一个系统的处理器 注意继承ChannelInitializer， 并实现它的唯一抽象方法initChannel
	 */
	class ServiceHandler extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {

			// 得到管道
			ChannelPipeline pipeline = ch.pipeline();

			// 设置解码， 这里设置以\n为标识的解码器
			pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
					Delimiters.lineDelimiter()));

			// 设置字符串解码与编码
			pipeline.addLast("decoder", new StringDecoder());
			pipeline.addLast("encoder", new StringEncoder());

			// 上面的解码器都是系统提供的，我们直接照搬即可

			// 绑定自己的逻辑处理器，定义在下面
			pipeline.addLast("handler", new MyHandler());

		}
	}

	/**
	 * 自己的逻辑处理器，注意继承SimpleChannelInboundHandler
	 */
	class MyHandler extends SimpleChannelInboundHandler<String> {

		// 此时就是服务器得到客户端发送的消息后进入的方法，可以理解为客户端每发一次消息，就进入这个方法
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, String msg)
				throws Exception {

			// 这里我们直接打印出客户端的message
			System.out.println(ctx.channel().remoteAddress() + "  发送了消息：    " + msg);
			// 通过ctx返回给客户端消息, 注意以\n结束
			ctx.writeAndFlush("接收到了你的消息，你好客户端！！！！\n");
		}

		// 同时覆盖channelAchive方法，表示如果有客户端连接时，进入的方法
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {

			// 同样做出一个打印
			System.out.println(ctx.channel().remoteAddress() + "    连接到服务器        ");
			map.put("1001", ctx.channel());
			// 同时返回一个消息给客户端
			ctx.writeAndFlush("欢迎访问服务器!!! 这里是  "
					+ InetAddress.getLocalHost().getHostName() + "  的服务站点\n");
			super.channelActive(ctx);
		}
		
		//闲置方法
		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			
			System.out.println("服务器宕机");
			super.channelInactive(ctx);
		}
		
	}

}

