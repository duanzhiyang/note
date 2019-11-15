package com.ming.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable{
	
	private Selector selector;
	private ByteBuffer buffer = ByteBuffer.allocate(1024);
	
	public Server(int port){
		try {
			//打开一个选择器
			selector = Selector.open();
			//打开服务器套接字通道。
			ServerSocketChannel ssc = ServerSocketChannel.open();
			//设置服务器为非阻塞方式  调整此通道的阻塞模式。
			ssc.configureBlocking(false);
			//将 ServerSocket 绑定到特定地址（IP 地址和端口号）。
			ssc.bind(new InetSocketAddress(port));
			/*
			 * 把服务器通道注册到多路复用选择器上，并监听阻塞状态
			 * 
			 * 向给定的选择器注册此通道，返回一个选择键。
			 *	此方法首先验证此通道是否已打开，以及给定的初始相关操作集是否有效。
			 *	如果已向给定的选择器注册了此通道，则在将其相关操作集设置为给定值后，返回表示该注册的选择键。
			 */
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("server start whit port:"+port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				//会这这里处理事件，也是阻塞的，事件包括客户端连接，客户端发送数据到来,以及客户端断开连接等等
                //若没有事件发生，也会阻塞  
				//选择一组键，其相应的通道已为 I/O 操作准备就绪。
				System.out.println("阻塞在这1");
				selector.select();
				System.out.println("阻塞在这2");
				//返回所有已经注册到多路复用选择器的通道的SelectionKey
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				while(keys.hasNext()){
					SelectionKey key = keys.next();
					keys.remove();
					//  告知此键是否有效。
					if(key.isValid()){
						if(key.isAcceptable()){  //请求连接事件  测试此键的通道是否已准备好接受新的套接字连接。
							System.out.println("连接");
							accept(key);   //处理新的客户连接
						}
						if(key.isReadable()){
							System.out.println("读写");
							read(key);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 处理客户端连接   服务器为买个客户端生成一个Channel Channel与客户端对接，Channel绑定到Selector上
	 * @param key
	 */
	private void accept(SelectionKey key){
		try {
			//获取之前注册的SocketChannel通道
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			//执行阻塞方法，Channel和客户端对接
			SocketChannel sc = ssc.accept();
			//设置模式为非阻塞
            sc.configureBlocking(false);
            sc.register(selector,SelectionKey.OP_READ);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void read(SelectionKey key){
		try {
			//清空缓存区的旧数据
			buffer.clear();
			SocketChannel sc = (SocketChannel)key.channel();
			int count = sc.read(buffer);
			if(count == -1){
				//关闭此通道。
				key.channel().close();
				//请求取消此键的通道到其选择器的注册
				key.cancel();
				return;
			}
			//读取到了数据，将buffer的position复位到0
            buffer.flip();
            //返回当前位置与限制之间的元素数。
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("Server:"+body);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args){
		new Thread(new Server(8379)).start();
	}
	

}
