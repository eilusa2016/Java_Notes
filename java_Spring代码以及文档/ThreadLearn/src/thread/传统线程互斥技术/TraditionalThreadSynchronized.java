package thread.传统线程互斥技术;

/**
 * 传统的线程的安全问题
 * 重点：
 *    线程之间的同步，必须用同一把锁来关联问题
 * <p>Title:TraditionalThreadSynchronized</p>
 * @date  2016年9月4日
 */
public class TraditionalThreadSynchronized {
	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
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
					//outputer.output2("lihuoming");
					outputer.output3("lihuoming");
				}
				
			}
		}).start();
		
	}
	
	static class Outputer{
		public void output(String name){
			int len=name.length();
			synchronized (Outputer.class) 
			{
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
			
		}
		public  synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		/**
		 * 此方法关联的对象是   Outputer.class
		 * 与字节码同步
		 * @param name
		 */
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		
	}
	
}
