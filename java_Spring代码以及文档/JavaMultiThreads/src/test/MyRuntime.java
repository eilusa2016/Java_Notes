package test;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRuntime {
	static AtomicInteger at=new AtomicInteger();
	public static void main(String[] args) {
		int ii = Integer.MAX_VALUE + 1;
//		at.incrementAndGet();
		// System.out.println(ii+"\n"+(int)(Math.pow(2,
		// 32))+"\nlongMax="+Long.MAX_VALUE);
		System.out.println(args.toString());
		for(int i=0;i<args.length;i++){
			System.out.println("ARGS["+i+"]:"+args[i].toString());
		}
		System.out.println("cpu数量："
				+ Runtime.getRuntime().availableProcessors());
//		 Collections.sort(list, c);
		
//		DemoString ds=new DemoString();
//		ds.setIndex(1);
//		ds.setLl(10l);
//		ds.setStr("world");
//		System.out.println("1:"+ds.toString());
//		test(ds);
//		System.out.println("3:"+ds.toString());
		

	}
	/**
	 * 测试 引用类型内部的致类型的对象的修改
	 * 是否会影响整个类实例对象
	 * @param ds
	 */
	private static void test(DemoString ds){
		if(ds==null)
			return;
		ds.setIndex(111);;
		ds.setLl(100l);
		ds.setStr("fuck");
		System.out.println("2:"+ds.toString());
	}
	
	/**
	 * java 可变参数列表
	 * 
	 * @param x
	 * @param args
	 * @return
	 */
	public static int add(int x, int... args) {
		int sum = x;
		for (int i = 0; i < args.length; i++) {
			sum += args[i];
		}
		return sum;
	}

	private static class DemoString {
		private int index;
		private long ll;
		private String str;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public long getLl() {
			return ll;
		}
		public void setLl(long ll) {
			this.ll = ll;
		}
		public String getStr() {
			return str;
		}
		public void setStr(String str) {
			this.str = str;
		}
		@Override
		public String toString() {
			return "DemoString [index=" + index + ", ll=" + ll + ", str=" + str
					+ "]";
		}

		
		
	}

}
