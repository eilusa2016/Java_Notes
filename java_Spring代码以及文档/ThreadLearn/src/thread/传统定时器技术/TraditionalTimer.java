package thread.传统定时器技术;

import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimer {
	public static void main(String[] args) {
		/**
		 * 传统的定时器
		 * 时间单位  毫秒
		 */
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				/**
				 * 运行的方法
				 */
				System.out.println("执行定时器的方法。。。。");
			}
		}, 3000,20);
	}
}
