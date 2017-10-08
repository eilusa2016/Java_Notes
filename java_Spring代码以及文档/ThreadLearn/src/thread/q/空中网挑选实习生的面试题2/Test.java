package thread.q.空中网挑选实习生的面试题2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class Test {
	/**
	 * 10个线程来消费生产者的数据
	 * 都调用 TestDo.doSome(input);方法
	 * 每次1秒
	 * 前一个消费完了  下一个才能进行消费
	 * @param args
	 */
	public static void main(String[] args) {
		//每次可以访问自身的线程数量为1 线程也排着队来执行
		final Semaphore semaphore = new Semaphore(1);
		//同步的队列   读走一个才能插入下一个(这个实际上是 那个线程快  就哪个先抢走数据)
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						String input = queue.take();
						String output = TestDo.doSome(input);
						
						System.out.println(Thread.currentThread().getName()
								+ ":" + output);
						semaphore.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) { // 这行不能改动
			String input = i + ""; // 这行不能改动
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

// 不能改动此TestDo类
class TestDo {
	public static String doSome(String input) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":" + (System.currentTimeMillis() / 1000);
		return output;
	}
}
