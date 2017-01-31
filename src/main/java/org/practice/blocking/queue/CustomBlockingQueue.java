package org.practice.blocking.queue;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue<E> implements CustomQueue<E> {

	private Object[] array;
	private int front;
	private int rear;
	private int size;
	private Lock lock;
	private int count;
	private final Condition QUEUE_FULL;
	private final Condition QUEUE_EMPTY;
	
	public CustomBlockingQueue(int capacity){
		this.array 			= new Object[capacity];
		this.front 			= -1;
		this.rear  			= -1;
		this.size  			= array.length;
		this.lock 			= new ReentrantLock();
		this.QUEUE_FULL 	= lock.newCondition();
		this.QUEUE_EMPTY 	= lock.newCondition();
		System.out.println("SIZE :"+size);
		
	}
	
	public void enQueue(E data) {
		try{
			lock.tryLock();
			if(isFull()){
				try {
					QUEUE_FULL.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				rear = (rear+1)%size;
				array[rear] = data;
				if(front == -1)
					front = rear;
				QUEUE_EMPTY.signalAll();
			}
			count++;
		}finally{
			if (((ReentrantLock) lock).isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
		
	}

	private boolean isFull() {
		return (count == size);
	}

	@SuppressWarnings("unchecked")
	public E deQueue() {
		E data = null;
		try {
			lock.tryLock();
			if(isEmpty()){
				try {
					QUEUE_EMPTY.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				data = (E) array[front];
				if(front == rear){
					front = rear = -1;
				}else{
					front = (front+1)%size;
				}
				QUEUE_FULL.signalAll();
			}
			count--;
		} finally {
			if (((ReentrantLock) lock).isHeldByCurrentThread()) {
				lock.unlock();
			}
			
		}
		return data;
	}

	private boolean isEmpty() {
		return (count == 0);
	}

	public int getSize(){
		return count;
	}
	@Override
	public String toString() {
		return "CustomBlockingQueue [array=" + Arrays.toString(array)
				+ ", size=" + size + ", count=" + count + "]";
	}

   
}
