package com.fanglei.thinking_in_java_book.chapter21_concurrency;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Daemon threads don't prevent the program from ending.
 *
 * Created by Owen on 2017/4/3.
 */
public class SimpleDaemons implements Runnable
{
	@Override
	public void run() {
		try {
			while(true)
			{
				TimeUnit.MILLISECONDS.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " " + this);
			}
		} catch (InterruptedException e)
		{
			System.out.println("Sleep() is interrupted.s");
		}
	}

	public static void main(String[] arg)
	{
		for(int i = 0; i < 10; i++)
		{
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}

		System.out.println("All daemons started.");
		
		try {
			TimeUnit.MILLISECONDS.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
