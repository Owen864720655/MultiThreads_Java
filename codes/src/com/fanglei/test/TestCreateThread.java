package com.fanglei.test;

/**
 * ����ThreadByExtendThread��ThreadByImplementRunable
 * 
 * @author Owen
 *
 */
public class TestCreateThread 
{
	public static void main(String[] args)
	{
		/* ͨ���̳�Thread�������߳�*/
		ThreadByExtendThread t1 = new ThreadByExtendThread();
		t1.start();
		
		/* ͨ��ʵ��Runnable�������߳�*/
		ThreadByImplementRunable r = new ThreadByImplementRunable();
		Thread t2 = new Thread(r);
		t2.start();
	}
}
