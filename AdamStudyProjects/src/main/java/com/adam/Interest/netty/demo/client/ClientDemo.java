package com.adam.Interest.netty.demo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientDemo {

	public static void main(String[] args) {
		new ClientDemo().startClient();
	}
	
	public void startClient(){
		//与服务端不同，此时客户端只需要创建一个group
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			//注意netty是基于abstractBootStrap的，所以服务器创建的是serverBootStrap
			//而客户端就创建bootstrap
			Bootstrap bootstrap = new Bootstrap();
			
			//给客户端绑定group
			bootstrap.group(group);
			//绑定channel
			bootstrap.channel(NioSocketChannel.class);
			//绑定handler处理器，同样，这个处理器我们定义在下面
			bootstrap.handler(new ClientHandler());
			
			//用channel连接服务器
			Channel channel = bootstrap.connect("183.230.8.172", 8010).sync().channel();
			
			while (true) {
				System.out.println("请输入");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				for (;;) {
	                String line = in.readLine();
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
			}
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//关闭group
			group.shutdownGracefully();
		}
		
	}
	
}

/**
 * 客户端的handler处理器
 */
class ClientHandler extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//与服务器一样，设置解码器，但是一定要注意务必与服务器保持一致
		ChannelPipeline pipeline = ch.pipeline();

        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         * 
         * */
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
		
        //然后同样要实现我们自己的逻辑处理
        pipeline.addLast("handler", new MyHandler());
	}
}


class MyHandler extends SimpleChannelInboundHandler<String>{

	//同样这里就是服务器每发送一次数据时，就调用这个方法
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		System.out.println("接收到来自服务器的消息          " + msg);
	}
	
	//连接服务器时进入的方法
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("已经连接到服务器");
		super.channelActive(ctx);
	}
	
	//客户端闲置的方法
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端闲置");
		super.channelInactive(ctx);
	}
	
}