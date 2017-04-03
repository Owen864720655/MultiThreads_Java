package com.fanglei.thinking_in_java_book.chapter21_concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Show the simple use of priorities
 *
 * Created by Owen on 2017/4/2.
 */
public class SimplePriorities implements Runnable
{
	private int countDown = 5;
	private volatile double d;
	private int priority;

	public SimplePriorities(int priority)
	{
		this.priority = priority;
	}

	public String toString()
	{
		return Thread.currentThread() + " : " + countDown;
	}

	public void run()
	{
		Thread.currentThread().setPriority(priority);
		while(true)
		{
			for(int i = 1; i < 10000; i++)
			{
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if(--countDown == 0) return;
		}
	}

	public static void main(String [] arg)
	{
		ExecutorService exec = Executors.newCachedThreadPool();

		for(int i = 0; i < 5; i++)
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));

		exec.shutdown();
	}
}
