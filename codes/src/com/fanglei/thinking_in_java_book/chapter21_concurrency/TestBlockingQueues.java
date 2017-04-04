package com.fanglei.thinking_in_java_book.chapter21_concurrency;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by Owen on 2017/4/4.
 */

class LiftOffRunner implements Runnable
{
	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> rockets)
	{
		this.rockets = rockets;
	}

	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			System.out.println("Interrupted during put()");
		}
	}

	public void run()
	{
		try {
			while(!Thread.interrupted())
			{
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e)
		{
			System.out.println("Waking for take()");
		}
		System.out.println("Existing LiftOffRunner");
	}
}


public class TestBlockingQueues
{
	static void getKey()
	{
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	static void getKey(String message)
	{
		System.out.println(message);
		getKey();
	}

	static void test(String msg, BlockingQueue<LiftOff> queue)
	{
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();

		for(int i = 0; i < 5; i++)
		{
			runner.add(new LiftOff(5));
		}
		getKey("Press 'Enter' (" + msg + ")");
		t.interrupt();
		System.out.println("Finished " + msg + " test");
	}

	public static void main(String [] arg)
	{
		test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());
	}
}
