package com.egdfrm.extend.common;

import java.util.Random;

public class RandomDigitalAndChar {
	
	public String Digital(int lengthNum) {
		Random rd = new Random(); // 创建随机对象
		String n = ""; // 保存随机数
		int rdGet; // 取得随机数
		do {
			rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
			char num1 = (char) rdGet; // int转换char
			String dd = Character.toString(num1);
			n += dd;
		} while (n.length() < lengthNum);// 设定长度
		return n;
	}

	public String DigitalAndChar(int lengthNum) {
		
		String n = ""; // 保存随机数
		int rdGet; // 取得随机数
		for (int i = 1; i <= lengthNum; i++) {
			int rd = new Random().nextInt(3);
			if (rd == 0) {
				rdGet = new Random().nextInt(9) + 48; // 产生48到57的随机数(0-9的键位值)
			} else if (rd == 1) {
				rdGet = new Random().nextInt(26) + 65; // 产生65到90的随机数(A-Z的键位值)
			} else {
				rdGet = new Random().nextInt(26) + 97; // 产生97到122的随机数(a-z的键位值)
			}
			char num1 = (char) rdGet; // int转换char
			String dd = Character.toString(num1);
			n += dd;
		}
		
		return n;
	}
	
	public String HexDigital(int lengthNum) {
		
		String n = ""; // 保存随机数
		int rdGet; // 取得随机数
		for (int i = 1; i <= lengthNum; i++) {
			int rd = new Random().nextInt(16);
			if (rd <=9 ) {
				rdGet = rd + 48; // 产生48到57的随机数(0-9的键位值)
			} else {
				rdGet = rd + 55; // 产生65到90的随机数(A-Z的键位值)
			}
			char num1 = (char) rdGet; // int转换char
			String dd = Character.toString(num1);
			n += dd;
		}
		
		return n;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomDigitalAndChar r = new RandomDigitalAndChar();
		String rnds = r.Digital(20);
		System.out.println(rnds);
		String rndstr = r.DigitalAndChar(20);
		System.out.println(rndstr);
		String rndHex = r.HexDigital(20);
		System.out.println(rndHex);
	}
	
}
