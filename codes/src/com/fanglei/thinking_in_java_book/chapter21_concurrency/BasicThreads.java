package com.fanglei.thinking_in_java_book.chapter21_concurrency;

/**
 * The most basic use of the Thread class
 *
 * Created by Owen on 2017/4/2.
 */
public class BasicThreads
{
	public static void main(String[] arg)
	{
		Thread t = new Thread(new LiftOff(12));
		t.start();
		System.out.println("Waiting for the LiftOff!");
	}
}
