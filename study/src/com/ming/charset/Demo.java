package com.ming.charset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;

public class Demo {
	public static void main(String[] args) throws  Exception{


        OutputStream output = null;
        try {
            // read bmpC:/Users/admin/Desktop
            BufferedImage img = ImageIO.read(new File("C:/Users/admin/Desktop/1474258599980.bmp"));
            int imageType = img.getType();
            int w = img.getWidth();
            int h = img.getHeight();
            int startX = 0;
            int startY = 0;
            int offset = 0;
            int scansize = w;
            // rgb的数组
            int[] rgbArray = new int[offset + (h - startY) * scansize
                    + (w - startX)];
            img.getRGB(startX, startY, w, h, rgbArray, offset, scansize);

            int x0 = w / 2;
            int y0 = h / 2;
            int rgb = rgbArray[offset + (y0 - startY) * scansize
                    + (x0 - startX)];
            Color c = new Color(rgb);
            System.out.println("中间像素点的rgb：" + c);
            // create and save to bmp
            File out = new File("C:/Users/admin/Desktop/2.bmp");
            if (!out.exists())
                out.createNewFile();
            output = new FileOutputStream(out);
            BufferedImage imgOut = new BufferedImage(w, h, imageType);
            imgOut.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
            for(int r :rgbArray){
                System.out.print(r);
            }
            //ImageIO.write(imgOut, "bmp", output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException e) {
                }
        }

//		/**德邦**/
//		String template = "SIZE 100mm,180 mm\r\n"+
//				"DIRECTION 0,0\r\n"+
//				"SET PEEL OFF\r\n"+
//				"SET TEAR ON\r\n"+
//				"CLS\r\n"+
//
//				"BITMAP 200,200,20,80,0,xxxxxx\r\n"+
//				"BAR 16,120,768,3\r\n"+
//				"BAR 680,8,2,112\r\n"+
//				"TEXT 682,48,\"9\",0,50,54,\"快递\"\r\n"+
//
//				"BAR 400,120,2,90\r\n" +
//				"TEXT 40,128,\"9\",0,22,24,\"代收金额：--\"\r\n" +
//				"TEXT 416,128,\"9\",0,22,24,\"保价金额：--\"\r\n" +
//				"TEXT 416,154,\"9\",0,22,24,\"签单返回：--\"\r\n" +
//				"TEXT 416,180,\"9\",0,22,24,\"始发网点：--\"\r\n" +
//				"BAR 16,208,768,3\r\n" +
//				"TEXT WANGDIANX,232,\"9\",0,60,65,\"RPROVINCECITY\"\r\n"+
//
//				"BAR 16,320,768,3\r\n"+
//				"[SHOU]\r\n"+
//				"TEXT 128,328,\"9\",0,24,26,\"RECEIVER    RPHONE\"\r\n"+
//				"TEXT 128,368,\"9\",0,24,26,\"RADDRESS1\"\r\n"+
//				"TEXT 128,408,\"9\",0,24,26,\"RADDRESS2\"\r\n"+
//				"BAR 16,440,768,3\r\n"+
//				"TEXT 48,472,\"9\",0,45,49,\"寄\"\r\n"+
//				"TEXT 128,448,\"9\",0,23,25,\"SENDER    SPHONE\"\r\n"+
//				"TEXT 128,478,\"9\",0,23,25,\"SADDRESS1\"\r\n"+
//				"TEXT 128,510,\"9\",0,23,25,\"SADDRESS2\"\r\n"+
//
//				"BAR 16,536,768,3\r\n"+
//				"BARCODE 104,560,\"128\",96,0,1,NARROW_WIDE1,\"WAYBILLNO\"\r\n"+
//				"TEXT 270,664,\"9\",0,32,35,\"WAYBILLNO\"\r\n"+
//				"BAR 16,712,768,3\r\n"+
//				"TEXT 24,720,\"9\",0,22,25,\"SEND_DATE\"\r\n"+
//				"TEXT 24,760,\"9\",0,22,25,\"SEND_TIME\"\r\n"+
//				"TEXT 24,800,\"9\",0,22,25,\"1/1\"\r\n"+
//				"TEXT 24,840,\"9\",0,22,25,\"打印时间\"\r\n"+
//				"TEXT 192,728,\"9\",0,16,18,\"快件送达收件人地址，经收件人或收件人（寄件人）允许的\"\r\n"+
//				"TEXT 192,752,\"9\",0,16,18,\"代收人签字，视为送达。您的签字代表您已经签收此包裹，\"\r\n"+
//				"TEXT 192,776,\"9\",0,16,18,\"并已确认商品信息无误、包装完好、没有划痕、破损等表面\"\r\n"+
//				"TEXT 192,800,\"9\",0,16,18,\"质量问题。\"\r\n"+
//				"TEXT 528,824,\"9\",0,19,22,\"签收栏\"\r\n"+
//				"BAR 176,712,2,160\r\n"+
//				"BAR 624,712,2,160\r\n"+
//				"QRCODE 648,736,Q,4,M,0,\"QRCODE_URL\"\r\n"+
//				"BARCODE 312,888,\"128\",40,0,1,NARROW_WIDE2,\"WAYBILLNO\"\r\n"+
//				"TEXT 470,936,\"9\",0,20,24,\"WAYBILLNO\"\r\n"+
//				"BAR 16,960,768,3\r\n"+
//
//				"TEXT 48,978,\"9\",0,45,49,\"收\"\r\n"+
//				"TEXT 128,968,\"9\",0,21,24,\"RECEIVER    RPHONE\"\r\n"+
//				"TEXT 128,1000,\"9\",0,18,20,\"RADDRESS1\"\r\n"+
//				"TEXT 128,1023,\"9\",0,18,20,\"RADDRESS2\"\r\n"+
//				"BAR 624,960,2,160\r\n"+
//				"QRCODE 648,984,Q,4,M,0,\"QRCODE_URL\"\r\n"+
//				"BAR 16,1040,608,3\r\n"+
//				"TEXT 48,1058,\"9\",0,45,49,\"寄\"\r\n"+
//				"TEXT 128,1052,\"9\",0,21,24,\"SENDER    SPHONE\"\r\n"+
//				"TEXT 128,1084,\"9\",0,18,20,\"SADDRESS1\"\r\n"+
//				"TEXT 128,1107,\"9\",0,18,20,\"SADDRESS2\"\r\n"+
//				"BAR 16,1120,768,3\r\n"+
//
//				"BARCODE 312,1136,\"128\",40,0,1,NARROW_WIDE2,\"WAYBILLNO\"\r\n"+
//				"TEXT 470,1184,\"9\",0,20,24,\"WAYBILLNO\"\r\n"+
//				"BAR 16,1208,768,3\r\n"+
//				"TEXT 48,1226,\"9\",0,45,49,\"收\"\r\n"+
//				"TEXT 128,1216,\"9\",0,21,24,\"RECEIVER    RPHONE\"\r\n"+
//				"TEXT 128,1248,\"9\",0,18,21,\"RADDRESS1\"\r\n"+
//				"TEXT 128,1271,\"9\",0,18,21,\"RADDRESS2\"\r\n"+
//				"BAR 624,1208,2,160\r\n"+
//				"QRCODE 648,1232,Q,4,M,0,\"QRCODE_URL\"\r\n"+
//				"BAR 16,1288,608,3\r\n"+
//				"TEXT 48,1306,\"9\",0,45,49,\"寄\"\r\n"+
//				"TEXT 128,1300,\"9\",0,21,24,\"SENDER    SPHONE\"\r\n"+
//				"TEXT 128,1332,\"9\",0,18,21,\"SADDRESS1\"\r\n"+
//				"TEXT 128,1355,\"9\",0,18,21,\"SADDRESS2\"\r\n"+
//
//				"BAR 16,1368,768,3\r\n"+
//				"PRINT 1,1\r\n";
//
//		while (true){
//			System.out.println(template);
//			Thread.sleep(2000L);
//		}
	}
}
