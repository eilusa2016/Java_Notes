package thread.e.线程并发库的应用_线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 线程池
 * <p>Title:ThreadPoolTest</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author Guardians
 * @date  2016年9月6日
 */
public class ThreadPoolTest {
//	Object d;
//	List<?> list;
//	ArrayList<?> ee;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ExecutorService threadPoolf = Executors.newFixedThreadPool(3);//固定的线程池
		// ExecutorService threadPool =
		// Executors.newCachedThreadPool();//自动增加任务的
		ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单线程的池子，但是线程死了，他会再弄出一个来，保证有一个线程在
		for (int i = 1; i <= 10; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 1; j <= 10; j++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()
								+ " is looping of " + j + " for  task of "
								+ task);
					}
				}
			});
		}
		System.out.println("all of 10 tasks have committed! ");
		// threadPool.shutdownNow();//立马关闭
		// threadPool.shutdown();//池子里面的任务结束再关闭

		// 定时器线程池，6秒后启动 以后间隔2秒运行
		/**
		 * 如果需要定时在今天几点执行
		 * 那么要自己计算，间隔时间为：date.getTime()-System.currentTimeMillis()
		 * 意思为，今天要执行的时间  减去  当前的时间    单位是 Millis(TimeUnit.MINUTES)
		 */
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()
						+ "：bombing!");

			}
		}, 6, 2, TimeUnit.SECONDS);
	}
}
