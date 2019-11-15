package com.ming.charset;

public class EncodeType {
	public static void main(String[] args) {
		encode();
	}
	
	public static void encode(){
		String name = "I am ¾ýÉ½";
		char[] chars = name.toCharArray();
		for(char c : chars){
			System.out.print(c);
		}
		try {
			byte[] iso8859 = name.getBytes("ISO-8859-1");
			System.out.println("============iso8859============");
			System.out.println(HexUtil.bytesToHexFun2(iso8859));
			System.out.println(HexUtil.bytesToHexFun2(iso8859));
			System.out.println("=============gb2312===========");
			byte[] gb2312 = name.getBytes("GB2312");
			System.out.println(HexUtil.bytesToHexFun2(gb2312));
			System.out.println(HexUtil.bytesToHexFun2(gb2312));
			System.out.println("=============gbk===========");
			byte[] gbk = name.getBytes("GBK");
			System.out.println(HexUtil.bytesToHexFun2(gbk));
			System.out.println(HexUtil.bytesToHexFun2(gbk));
			System.out.println("============utf16============");
			byte[] utf16 = name.getBytes("UTF-16");
			System.out.println(HexUtil.bytesToHexFun2(utf16));
			System.out.println(HexUtil.bytesToHexFun2(utf16));
			System.out.println("============utf8============");
			byte[] utf8 = name.getBytes("UTF-8");
			System.out.println(HexUtil.bytesToHexFun2(utf8));
			System.out.println(HexUtil.bytesToHexFun2(utf8));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
