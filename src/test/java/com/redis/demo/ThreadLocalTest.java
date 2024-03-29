package com.redis.demo;

public class ThreadLocalTest {
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<String>();
	
	public void set() {
		this.longLocal.set(Thread.currentThread().getId());
		this.stringLocal.set(Thread.currentThread().getName());
	}
	
	public long getLong() {
		return longLocal.get();
	}
	
	public String getString() {
		return this.stringLocal.get();
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final ThreadLocalTest test = new ThreadLocalTest();
		
		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());
		
		Thread thread1 = new Thread() {
			public void run() {
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			}
		};
		thread1.start();
		thread1.join();
		
		System.out.println(test.getLong());
		System.out.println(test.getString());
	}

}
