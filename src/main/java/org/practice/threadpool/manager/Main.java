package org.practice.threadpool.manager;

import org.practice.blocking.queue.CustomBlockingQueue;

public class Main {

	public static void main(String[] args) {

		/*CustomBlockingQueue<Integer> customBlockingQueue = new CustomBlockingQueue<Integer>(10);
		for(int i  =1; i <= 10; i++){
			customBlockingQueue.enQueue(i);
		}
		
		int size = customBlockingQueue.getSize();
		for(int i = 0; i <= size; i++){
			System.out.println(customBlockingQueue.deQueue());
		}
		*/
		
		ThreadPoolExecutor threadPool = ThreadPoolManager.initializeThreadPool(10);
		
//		for(int i = 1; i <= 10; i++){
//			final int number = i;
//			Runnable r = new Runnable() {
//				
//				public void run() {
//					System.out.println("i == "+number);
//					
//				}
//			};
//			
//			threadPool.executeTask(r);
//		}
		
	}
}
