package com.fanglei.test;

/**
 * ͨ��extend Thread��ʵ���߳�
 * @author Owen
 * @version 2017-03-31
 *
 */
public class ThreadByExtendThread extends Thread 
{
	
	/**
	 * ʵ��run()����
	 */
	public void run()
	{
		System.out.println("I'am in a thread that extends Thread!");
	}
	
}
