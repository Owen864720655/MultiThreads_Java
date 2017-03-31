package com.fanglei.test;

/**
 * 通过extend Thread来实现线程
 * @author Owen
 * @version 2017-03-31
 *
 */
public class ThreadByExtendThread extends Thread 
{
	
	/**
	 * 实现run()函数
	 */
	public void run()
	{
		System.out.println("I'am in a thread that extends Thread!" + " The thread name is " + currentThread().getName());
	}
	
}
