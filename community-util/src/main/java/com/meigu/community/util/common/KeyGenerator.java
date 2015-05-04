package com.meigu.community.util.common;

import java.util.UUID;

public class KeyGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for(int i=0;i<=6;i++)
//			System.out.println(UUIDGenerator());
		
		StringBuffer sql = new StringBuffer("insert ,into ,values ,");
		
		int i= sql.lastIndexOf(",");
		System.out.println(i);
		sql.replace(0,i,  sql.toString());
		System.out.println();
		
	}

	public static String UUIDGenerator(){
		String uuid=UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
	
	public static int getRandomIntId(){
		long time=System.currentTimeMillis();
		String str=String.valueOf(time);
		str=str.substring(8, str.length())+((int)(Math.random()*1000));
		return Integer.parseInt(str);
	}
	
	public static String getGroupCode(){
		long time=System.currentTimeMillis();
		String str=String.valueOf(time);
		str=str.substring(6, str.length())+((int)(Math.random()*1000));
		if(str.length()==9)str+="0";
		return str;
	}
	
}
