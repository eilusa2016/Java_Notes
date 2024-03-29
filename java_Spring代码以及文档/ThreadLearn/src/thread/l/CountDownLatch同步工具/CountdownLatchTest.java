package thread.l.CountDownLatch同步工具;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时 计时器
 * 计数器计数为0 的时候  才能继续往下走
 */
public class CountdownLatchTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						System.out.println("线程"
								+ Thread.currentThread().getName() + "正准备接受命令");
						cdOrder.await();//等待这个计数器到0的时候  线程继续往下走
						System.out.println("线程"
								+ Thread.currentThread().getName() + "已接受命令");
						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "回应命令处理结果");
						cdAnswer.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		try {
			Thread.sleep((long) (Math.random() * 10000));

			System.out.println("线程" + Thread.currentThread().getName()
					+ "即将发布命令");
			cdOrder.countDown();
			System.out.println("线程" + Thread.currentThread().getName()
					+ "已发送命令，正在等待结果");
			cdAnswer.await();//等待cdAnswer 这个计数器为0的时候  继续往下走
			System.out.println("线程" + Thread.currentThread().getName()
					+ "已收到所有响应结果");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();

	}
}
