package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable{
	//多路复用器
	private Selector selector;
	/*
	 * allocate(256) 创建一个容量为256字节的ByteBuffer
	 * 分配一个新的字节缓冲区
	 * 新缓冲区的位置将为零，其界限将为其容量，其标记是不确定的。
	 * 无论它是否具有底层实现数组，其标记都是不确定的
	 */
	private ByteBuffer buffer = ByteBuffer.allocate(1024);
	
	public Server(int port) {
		try {
			//open()创建选择器。
			selector = Selector.open();
			//ServerSocketChannel 针对面向流的侦听套接字的可选择通道。
			ServerSocketChannel ssc = ServerSocketChannel.open();
			//设置服务器为非阻塞方式
			ssc.configureBlocking(false);
			ssc.bind(new InetSocketAddress(port));
			//把服务器通道注册到多路复用选择器上，并监听阻塞状态
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("Server start whit port:"+port);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void run() {
		while(true) {
			try {
				//这里处理事件，也是阻塞的，事件包括客户端连接，客户端数据到来，及客户端断开连接等等
				//若没有事件发生，也会阻塞
				//select() 这个方法会一直阻塞到某个注册的通道有事件就绪
				selector.select();
				System.out.println("阻塞在这");
				//返回所有已经注册到多路复用器的通道的selectionKey
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				while(keys.hasNext()) {
					SelectionKey key = keys.next();
					/*
					 * Selector.select()取出事件集中的全部事件，如果不删除，在下次轮询的时候，调用Selector.select()会取出旧的事件集
					 * ，导致重复处理
					 */
					keys.remove();
					if(key.isValid()) {  //判断key是否有效
						if(key.isAcceptable()) { //请求连接事件
							accept(key);  //处理新客户端的连接
						}
						if(key.isReadable()) {  //有数据到来
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
	 * 处理客户端连接
	 * 服务器为每个客户端生成一个Channel
	 * Channel与客户端对接
	 * Channel绑定到Selector上
	 * @param key
	 */
	private void accept(SelectionKey key) {
		try {
			//获取之前注册的SocketChannel通道
			ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
			//执行阻塞方法，Channel和客户端对接
			SocketChannel sc = ssc.accept();
			//设置模式为非阻塞
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private void read(SelectionKey key) {
		try {
			//清空缓冲区的旧数据
			buffer.clear();
			SocketChannel sc = (SocketChannel)key.channel();
			int count = sc.read(buffer);
			if(count == -1) {
				key.channel().close();
				key.cancel();
				return;
			}
			//读取到了数据，将buffer的position复位到0
			/*
			 * 使缓冲区为一系列新的通道写入或相对获取 操作做好准备：它将限制设置为当前位置，
			 * 然后将位置设置为 0
			 */
            buffer.flip();
            //remaing() 返回当前位置与限制之间的元素数。返回 此缓冲区中的剩余元素数
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("Server:"+body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Server(8379)).start();
	}

}
