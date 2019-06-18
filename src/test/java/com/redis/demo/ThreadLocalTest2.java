package com.redis.demo;

public class ThreadLocalTest2 {
	
	ThreadLocal<Thread> thread = new ThreadLocal<Thread>() {
		protected Thread initialValue() {
			return Thread.currentThread();
		}
	};
	
	public void set() {
		this.thread.set(Thread.currentThread());
	}
	
	public long getLong() {
		return this.thread.get().getId();
	}
	
	public String getString() {
		return this.thread.get().getName();
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final ThreadLocalTest2 test = new ThreadLocalTest2();
		
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
