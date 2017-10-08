package thread.n.阻塞队列的应用;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列来实现 线程的互斥
 * 
 * @date 2016年9月10日
 */
public class BlockingQueueCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		内部类的实例化方式(非静态)
//		final BlockingQueueCommunication.Business business = new BlockingQueueCommunication().new Business();
		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}

			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}

	}

	static class Business {
		//建议客户使用ConcurrentHashMap或在使用HanshMap的时候加上同步标志 解决同步的问题
//		ConcurrentHashMap<String, String> map=new ConcurrentHashMap<String, String>();
//		HashMap<String, String> map=(HashMap<String, String>) Collections.synchronizedMap(new HashMap<String, String>());
		
		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
		
		{
			try {
				System.out.println("xxxxxdfsdsafdsa");
				//首先先让第二个队列执行put一次 ，让其阻塞
				queue2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void sub(int i) {
			try {
				queue1.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int j = 1; j <= 10; j++) {
				System.out.println("sub thread sequece of " + j + ",loop of "
						+ i);
			}
			try {
				queue2.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void main(int i) {
			try {
				queue2.put(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int j = 1; j <= 100; j++) {
				System.out.println("main thread sequece of " + j + ",loop of "
						+ i);
			}
			try {
				queue1.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
