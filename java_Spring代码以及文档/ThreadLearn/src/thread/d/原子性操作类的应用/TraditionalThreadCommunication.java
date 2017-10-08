package thread.d.原子性操作类的应用;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 子线程循环10次， 接着主线程循环100， 接着又回到子线程循环10次， 接着再回到主线程又循环100， 如此循环50次，请写出程序
 * 这个是改写了例子后 使用并发库的
 * @date 2016年9月4日
 */
public class TraditionalThreadCommunication {
	/**
	 * 有可能被多个线程跳用的变量就用这个对象
	 * 局部变量不用使用这个
	 */
	AtomicInteger age=new AtomicInteger(0);
	
	public static void main(String[] args) {
		new TraditionalThreadCommunication().init();
	}

	public void init() {
		final Business business = new Business();

		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 1; i <= 50; i++) {
					//age.addAndGet(1);
					business.sub(i);
				}

			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	class Business {
		//确定执行 主方法  还是 子方法
		private boolean bShouldSub = true;

		public synchronized void sub(int i) {
			while(!bShouldSub) {
				try {
					//使得子线程沉睡
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//子线程循环10次
			for (int j = 1; j <= 10; j++) {
				System.out.println("sub thread sequence of " + j + ",loop of "
						+ i);
			}
			bShouldSub = false;
			//子线程处理完毕，需要唤醒这个同步锁
			this.notify();
		}

		public synchronized void main(int i) {
			while (bShouldSub) {
				try {
					//使得主线程（模拟的）沉睡
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int j = 1; j <= 100; j++) {
				System.out.println("main thread sequence of " + j + ",loop of "
						+ i);
			}
			bShouldSub = true;
			//再次唤醒子线程
			this.notify();
		}
	}
}
