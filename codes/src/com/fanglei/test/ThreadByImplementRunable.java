package com.fanglei.test;

/**
 * ʵ��Runnable�ӿ�����run����
 *  
 * @author Owen
 * @version 2017-03-31
 */

public class ThreadByImplementRunable implements Runnable 
{
	/**
	 * ʵ��run()
	 */
	public void run()
	{
		System.out.println("I'm in a thread that implements Runnable!");
	}

}
