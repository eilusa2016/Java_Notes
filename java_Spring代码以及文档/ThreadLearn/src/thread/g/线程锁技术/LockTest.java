package thread.g.线程锁技术;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程锁的应用
 * 锁住线程  在执行完（解锁）前 其他线程不能进入锁住的方法或者代码内
 * <p>Title:LockTest</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author Guardians
 * @date  2016年9月6日
 */
public class LockTest {
	
	public static void main(String[] args) {
		new LockTest().init();
	}
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
				
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("lihuoming");
				}
				
			}
		}).start();
		
	}

	static class Outputer{
		Lock lock = new ReentrantLock();
		public void output(String name){
			int len = name.length();
			lock.lock();//锁住
			try{
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}finally{
				lock.unlock();//解锁
			}
		}
		
		public synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}	
	}
}
