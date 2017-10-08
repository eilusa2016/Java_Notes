package thread.i.条件阻塞Condition的应用;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 改写 10次  100次的那个例子
 * <p>Title:ConditionCommunication</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author Guardians
 * @date  2016年9月6日
 */
public class ConditionCommunication {
	public static void main(String[] args) {
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
		Lock lock = new ReentrantLock();
		//这个是lock之上的
		Condition subcondition = lock.newCondition();
		Condition maincondition = lock.newCondition();
		private boolean bShouldSub = true;

		public void sub(int i) {
			lock.lock();
			try {
				while (!bShouldSub) {
					try {
						subcondition.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread sequence of " + j
							+ ",loop of " + i);
				}
				bShouldSub = false;
				maincondition.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (bShouldSub) {
					try {
						maincondition.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j
							+ ",loop of " + i);
				}
				bShouldSub = true;
				subcondition.signal();
			} finally {
				lock.unlock();
			}
		}

	}
}
