package com.sphz.util;

public class DoubleUtils {
	//判断double是否为0
	public static boolean doubleIsEquals0(double d){
		boolean b=true;
		if(Math.abs(d)<0.000001){
			
		}else{
			b=false;
		}
		return b;
	}
	public static double percent2Double(double percent){
		return percent/100;
	}
}
