package thread.传统线程技术回顾;

/**
 * 传统线程的练习
 * <p>Title:TraditionalThread</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author Guardians
 * @date  2016年9月4日
 */
public class TraditionalThread {
	public static void main(String[] args) {
		Thread thread=new Thread(){
			
			@Override
			public void run() {
				while(true){
					System.out.println("1:"+Thread.currentThread().getName());
					//这一条慎用
					//System.out.println("2:"+this.getName());
					
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
}
