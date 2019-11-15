package com.ming.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializeDemo {
	public static void main(String[] args) {
		//序列化
//		People p = new People();
//		p.setAge(18);
//		p.setName("小明");
//		p.setSex((byte)1);
//		
//		ObjectOutputStream oos = null;
//		try {
//			oos = new ObjectOutputStream(new FileOutputStream("tempFile4.dat"));
//			oos.writeObject(p);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		//反序列化
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("tempFile"));
			People p = (People)ois.readObject();
			System.out.println(p.getAge());
			System.out.println(p.getName());
			System.out.println(p.getSex());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
