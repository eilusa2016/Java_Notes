package thread.r.空中网挑选实习生的面试题3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 不能改动此Test类 
 * 第三题：现有程序同时启动了4个线程去调用
 * TestDo.doSome(key, value)方法，由于TestDo.doSome(key,value)方法内的代码是先暂停1秒，
 * 然后再输出以秒为单位的当前时间值，所以，会打印出4个相同的时间值，
 * 如下所示： 4:4:1258199615
 * 			1:1:1258199615 
 * 			3:3:1258199615 
 * 			1:2:1258199615
 * 请修改代码，如果有几个线程调用TestDo.doSome(key, value)方法时，
 * 传递进去的key相等（equals比较为true），则这几个线程应互斥排队输出结果
 * ，即当有两个线程的key都是"1"时，它们中的一个要比另外其他线程晚1秒输出结果，
 * 	如下所示： 4:4:1258199615 
 * 			 1:1:1258199615
 * 			 3:3:1258199615 
 * 			 1:2:1258199616
 * 总之，当每个线程中指定的key相等时，这些相等key的线程应每隔一秒依次输出时间值（要用互斥），
 * 如果key不同，则并行执行（相互之间不互斥）。原始代码如下：
 */
public class Test extends Thread {

	private TestDo testDo;
	private String key;
	private String value;

	public Test(String key, String key2, String value) {
		this.testDo = TestDo.getInstance();
		/*
		 * 常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
		 * 以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果
		 */
		this.key = key+ key2;
		/*
		 * a = "1"+""; b = "1"+""
		 */
		this.value = value;
	}

	public static void main(String[] args) throws InterruptedException {
		Test a = new Test("1", "", "1");
		Test b = new Test("1", "", "2");
		Test c = new Test("3", "", "3");
		Test d = new Test("4", "", "4");
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		a.start();
		b.start();
		c.start();
		d.start();

	}

	public void run() {
		testDo.doSome(key, value);
	}
}

class TestDo {

	private TestDo() {
	}

	private static TestDo _instance = new TestDo();

	public static TestDo getInstance() {
		return _instance;
	}

//	 private ArrayList keys = new ArrayList();
	private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();

	public void doSome(Object key, String value) {
		Object o = key;
		//判断是否已经存在这个key了
		if (!keys.contains(o)) {
			keys.add(o);
		} else {
			//已经存在的情况下，需要用到和它相等  但是需要互斥的对象
			for (Iterator iter = keys.iterator(); iter.hasNext();) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object oo = iter.next();
				if (oo.equals(o)) {
					o = oo;
					break;
				}
			}
		}
		synchronized (o)
		// 以大括号内的是需要局部同步的代码，不能改动!
		{
			try {
				Thread.sleep(1000);
				/**
				 * 这里会有一个问题
				 * 那就是  因为是 处以1000 取整的
				 * 所以 如果  4个时间
				 * 				100000997
				 * 				100000998
				 * 				100000999
				 * 				100001000 这个直就是个临界值
				 * 				打印出来  四个都是看起来同一个时间
				 * 				
				 */
				System.out.println(key + ":" + value + ":"
						+ (System.currentTimeMillis() / 1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
