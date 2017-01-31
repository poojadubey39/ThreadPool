package org.practice.threadpool.manager;

import org.practice.blocking.queue.CustomBlockingQueue;
import org.practice.blocking.queue.CustomQueue;

public class ThreadPoolExecutor {

	private CustomQueue<Runnable> customBlockingQueue;
	
	public ThreadPoolExecutor(int maxThreadCount) {
		customBlockingQueue = new CustomBlockingQueue<Runnable>(maxThreadCount);
		initializeWorkerThreads(maxThreadCount);
	}
	
	private void initializeWorkerThreads(int maxThreadCount) {
		
		for(int i = 1;i <= maxThreadCount; i++){
			new Thread(new WorkerThread(customBlockingQueue)).start();
		}
		
	}

	public void executeTask(Runnable task){
		customBlockingQueue.enQueue(task);
	}
	
}
