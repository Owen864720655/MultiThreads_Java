package com.fanglei.test.ducksale;

/**
 * @author zejian
 * @time 2016年3月13日 上午10:35:05
 * @decrition 多生产者多消费者模式
 */
public class Multi_Producer_Consumer {

	public static void main(String[] args)
	{
		KaoYaResource r = new KaoYaResource();
		Multi_Producer pro = new Multi_Producer(r);
		Multi_Consumer con = new Multi_Consumer(r);
		//生产者线程
		Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		//消费者线程
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		//启动线程
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
}
/**
 * @author zejian
 * @time 2016年3月12日 下午11:02:22
 * @decrition 生产者线程
 */
class Multi_Producer implements Runnable
{
	private KaoYaResource r;
	Multi_Producer(KaoYaResource r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.product("北京烤鸭");
		}
	}
}
/**
 * @author zejian
 * @time 2016年3月12日 下午11:02:05
 * @decrition 消费者线程
 */
class Multi_Consumer implements Runnable
{
	private KaoYaResource r;
	Multi_Consumer(KaoYaResource r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.consume();
		}
	}
}

