package org.practice.threadpool.manager;

import org.practice.blocking.queue.CustomBlockingQueue;
import org.practice.blocking.queue.CustomQueue;

public class ThreadPoolExecutor {

	private static CustomQueue<Runnable> customBlockingQueue;
	private static int maxThreadCount;
	private static int currentThreadCount;
	
	public ThreadPoolExecutor(int maxThreadCount) {
		customBlockingQueue = new CustomBlockingQueue<Runnable>(maxThreadCount);
		ThreadPoolExecutor.maxThreadCount = maxThreadCount;
	}


	public void executeTask(Runnable task){
		customBlockingQueue.enQueue(task);
		if(currentThreadCount != maxThreadCount) {
			new Thread(new WorkerThread(customBlockingQueue)).start();
			currentThreadCount++;
		}

	}
	
}
