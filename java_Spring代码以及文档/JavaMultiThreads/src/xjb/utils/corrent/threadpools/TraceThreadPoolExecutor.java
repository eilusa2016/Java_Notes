package xjb.utils.corrent.threadpools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
 * <p>Title:TraceThreadPoolExecutor</p>
 * <p>Description:重新自定义的堆栈线程池   不会被吃掉线程信息 </p>
 * <p>Company: 为了少加班</p> 
 * @author Guardians
 * @date  2017年1月8日
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

	public static void main(String[] args) {
		ThreadPoolExecutor pools=new TraceThreadPoolExecutor(0, 
												Integer.MAX_VALUE, 0l, 
												TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		for(int i=0;i<5;i++){
			final int ii=i;
			pools.execute(new Runnable() {
				int a=100,b=ii;
				@Override
				public void run() {
					// TODO Auto-generated method stub
					double re=a/b;
					System.out.println(re);
				}
			});
		}
	}
	
	
	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
	}
	
	@Override
	public Future<?> submit(Runnable task) {
		// TODO Auto-generated method stub
		return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
	}
	
	private  Exception clientTrace(){
		return new Exception("Client stack trace Exception");
	}
	
	/**
	 * 自己定义返回
	 * 以及处理抛出trace异常的方法
	 * @param task
	 * @param clintException 异常，保存提交任务的线程的 堆栈信息
	 * @param clinetThreadName
	 * @return
	 */
	private Runnable wrap(final Runnable task,final Exception clintException,String clinetThreadName){
		return new Runnable() {
			
			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					clintException.printStackTrace();
					throw e;
				}
				
			}
		};
	}
	
	
	
}
