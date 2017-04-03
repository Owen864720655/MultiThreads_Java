package com.fanglei.thinking_in_java_book.chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Call sleep() to pause for a while
 * Created by Owen on 2017/4/2.
 */
public class SleepingTask extends LiftOff
{
	public void run()
	{
		try {
			while(countDown-- > 0)
			{
				System.out.println(status());
//				TimeUnit.MICROSECONDS.sleep(1000);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e)
		{
			System.err.println(e);
		}
	}

	public static void main(String [] arg)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
		{
			exec.execute(new SleepingTask());
		}

		exec.shutdown();
	}
}
