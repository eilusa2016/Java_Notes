package thread.f.Callable与Future的应用;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableAndFuture {
	public static void main(String[] args) throws Exception, ExecutionException {
		ExecutorService executePoool = Executors.newSingleThreadExecutor();
		// 使用submint
		Future<String> future = executePoool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello callable !";
			}
		});
		System.out.println("等待接受数据。。。");
		// System.out.println("拿到的数据：" + future.get());
		System.out.println("拿到的数据：" + future.get(10, TimeUnit.SECONDS));
		// 以上的方式不知道有什么用，不能异步获取数据
		executePoool.shutdown();
		
		
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
				threadPool2);
		//十个任务
		for (int i = 1; i <= 10; i++) {
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
			});
		}
		
		
		System.out.println("已经执行了10个任务");
		
		for (int i = 0; i < 10; i++) {
			try {
				//拿到最先执行完的数据
				System.out.println(completionService.take().get());
				System.out.println("第"+i+"次等待获得数据完成");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("finished：10个任务");
		threadPool2.shutdownNow();
	}
}
