package com.fanglei.thinking_in_java_book.chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newFixedThreadPool(int numThreads)
 * Created by Owen on 2017/4/2.
 */
public class FixedThreadPool {

	public static void main(String[] arg)
	{
		int numThreads = 5;
		ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

		for (int i = 0; i < numThreads; i++)
		{
			executorService.execute(new LiftOff());
		}

		executorService.shutdown();
	}
}
