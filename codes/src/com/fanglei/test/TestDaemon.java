package com.fanglei.test;

/**
 * 测试守护线程Daemon
 *
 * @version 2017-03-31
 * Created by Owen on 2017/3/31.
 */
public class TestDaemon
{
	public static void main(String[] arg)
	{
		Thread deamon = new Thread(new DaemonRunner(), "DeamonRunner");

		//设置为守护线程
		deamon.setDaemon(true);
		deamon.start();
	}

	static class DaemonRunner implements Runnable{

		public void run()
		{
			try {
				Thread.sleep(500);
				System.out.println("执行try中的代码");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("这里的代码不一定会执行！");

			}
		}
	}
}
