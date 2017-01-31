package org.practice.blocking.queue;

public interface CustomQueue<E> {
	
	public void enQueue(E data);
	
	public E deQueue();
	
}
