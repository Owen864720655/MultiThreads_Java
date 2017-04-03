package com.fanglei.test;

import com.fanglei.test.ticket.TicketRight;

/**
 * Created by Owen on 2017/3/31.
 */
public class TestTicketRight
{
	public static void main(String[] arg)
	{
		/* 创建一个线程任务对象 */
		TicketRight t = new TicketRight();

		/* 创建4个线程同时卖票*/
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		Thread t3 = new Thread(t);
		Thread t4 = new Thread(t);

		/*启动4个线程*/
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}
}
