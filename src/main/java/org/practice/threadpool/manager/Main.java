package org.practice.threadpool.manager;

public class Main {

	public static void main(String[] args) throws Exception{

		ThreadPoolExecutor threadPool = ThreadPoolManager.initializeThreadPool(10);
		
		for(int i = 1; i < 10; i++){
			final int number = i;
			Runnable r = new Runnable() {

				public void run() {
					System.out.println(Thread.currentThread().getName());

				}
			};
			threadPool.executeTask(r);
		}
		
	}
}
