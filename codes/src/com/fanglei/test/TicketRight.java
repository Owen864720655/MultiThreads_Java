package com.fanglei.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟买票线程
 *
 *
 * Created by Owen on 2017/3/31.
 */
public class TicketRight implements Runnable
{
	/**
	 * 当前拥有的票数
	 */
	private int num = 100;

	//创建锁对象
	private Lock tickerLock = new ReentrantLock();


	public void run()
	{
		while (true)
		{
			tickerLock.lock(); //获取锁

			if (num > 0)
			{
				try {

					Thread.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); //出现异常就中断
				} finally {
					tickerLock.unlock(); //释放锁
				}

				System.out.println(Thread.currentThread().getName() + "...... sale ......" + (num--));

			}
		}
	}
}
