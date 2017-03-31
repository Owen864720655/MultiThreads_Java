package com.fanglei.test;

/**
 * 调用ThreadByExtendThread和ThreadByImplementRunable
 * 
 * @author Owen
 *
 */
public class TestCreateThread 
{
	public static void main(String[] args)
	{
		/* 通过继承Thread来启动线程*/
		ThreadByExtendThread t1 = new ThreadByExtendThread();
		t1.start();
		
		/* 通过实现Runnable来启动线程*/
		ThreadByImplementRunable r = new ThreadByImplementRunable();
		Thread t2 = new Thread(r);
		t2.start();
	}
}
