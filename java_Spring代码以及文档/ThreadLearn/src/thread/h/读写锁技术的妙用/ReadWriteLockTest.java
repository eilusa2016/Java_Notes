package thread.h.读写锁技术的妙用;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * <p>
 * Title:ReadWriteLockTest
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Guardians
 * @date 2016年9月6日
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for (int i = 0; i < 3; i++) {
			//读的线程
			new Thread() {
				public void run() {
					while (true) {
						q3.get();
					}
				}

			}.start();
			//写的线程
			new Thread() {
				public void run() {
					while (true) {
						q3.put(new Random().nextInt(10000));
					}
				}

			}.start();
		}

	}
}

class Queue3 {
	// 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	private Object data = null;
	ReadWriteLock rwl = new ReentrantReadWriteLock();

	public void get() {
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()
					+ " be ready to read data!");
			Thread.sleep((long) (Math.random() * 1000));
			System.out.println(Thread.currentThread().getName()
					+ "have read data :" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
	}

	public void put(Object data) {

		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()
					+ " be ready to write data!");
			Thread.sleep((long) (Math.random() * 1000));
			this.data = data;
			System.out.println(Thread.currentThread().getName()
					+ " have write data: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}

	}
}
