package com.fanglei.test.ticket;

/**
 * 模拟买票线程
 *
 *
 * Created by Owen on 2017/3/31.
 */
public class TicketWrong implements Runnable
{
	/**
	 * 当前拥有的票数
	 */
	private int num = 100;

	public void run()
	{
		while (true)
		{
			if (num > 0)
			{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}

				System.out.println(Thread.currentThread().getName() + "...... sale ......" + (num--));

			}
		}
	}
}
