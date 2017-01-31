package org.practice.threadpool.manager;

public class ThreadPoolManager {

	public static ThreadPoolExecutor initializeThreadPool(int capacity){
		return new ThreadPoolExecutor(capacity);
		
	}
}
