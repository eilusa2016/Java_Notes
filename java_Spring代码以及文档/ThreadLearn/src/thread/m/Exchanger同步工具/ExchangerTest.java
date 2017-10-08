package thread.m.Exchanger同步工具;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 两个线程之间的数据交换
 * <p>
 * Title:ExchangerTest
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Guardians
 * @date 2016年9月10日
 */
public class ExchangerTest {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		// 交换的数据类型为String
		final Exchanger<String> exchanger = new Exchanger<String>();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {

					String data1 = "zxx";
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在把数据" + data1 + "换出去");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据为" + data2);
				} catch (Exception e) {

				}

			}
		};
		executor.execute(runnable);
		// 线程2
		executor.execute(new Runnable() {
			public void run() {
				try {

					String data1 = "lhm";
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在把数据" + data1 + "换出去");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据为" + data2);
				} catch (Exception e) {

				}
			}
		});
		executor.shutdown();
	}
}
