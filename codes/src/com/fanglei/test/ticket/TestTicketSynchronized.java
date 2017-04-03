package com.fanglei.test.ticket;

import com.fanglei.test.ticket.TicketSynchronized;

/**
 * Created by Owen on 2017/3/31.
 */
public class TestTicketSynchronized
{

	public static void main(String[] arg)
	{
		/* 创建一个线程任务对象 */
		TicketSynchronized t = new TicketSynchronized();

		/* 创建4个线程同事卖票*/
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
