package com.other.test;

import java.util.Date;

public class TT {

	public static void ss(String ss1) {
		ss1 = "welcon";
	}

	public static void ss2(StringBuffer ss1) {
		ss1 = new StringBuffer("11");
	}

	public static boolean foo(char ss) {

		System.out.println(ss);
		return true;
	}

	public static void main(String[] args) {
		// int i=0;
		// for(foo('A');foo('B')&&i<2;foo('c')){
		// i++;
		// foo('D');
		// }
		// Person p=new Person();
		// p.setAge(10);
		// p.setName("hh");
		// p.setBirth(new Date());
		// System.out.println(p.toString());
		//
		// mp(p);
		// System.out.println(p.toString());
		String str1 = new String("abc");
		String str2 = new String("ABC");
		int a = str2.compareTo(str1);// a>0
		int b = str1.compareTo(str2);// b=0
		System.out.println("a="+a+";\nb="+b);
		boolean c = str1.equals(str2);// c=false
		boolean d = str1.equalsIgnoreCase(str2);// d=true
	}

	private static void mp(Person pp) {
		pp.setAge(20);
		pp.setName("rr");
		pp.setBirth(new Date(444444444l));
	}

	// String s1="hello";
	// StringBuffer s2=new StringBuffer("22");
	// ss(s1);
	// ss2(s2);
	// System.out.println(s1);
	// System.out.println(s2);
	// }
}
