package com.fanglei.test;

/**
 * 实现Runnable接口重新run方法
 *  
 * @author Owen
 * @version 2017-03-31
 */

public class ThreadByImplementRunable implements Runnable 
{
	/**
	 * 实现run()
	 */
	public void run()
	{
		System.out.println("I'm in a thread that implements Runnable!");
	}

}
