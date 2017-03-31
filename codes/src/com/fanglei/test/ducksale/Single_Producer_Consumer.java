package com.fanglei.test.ducksale;

/**
 * @author zejian
 * @time 2016年3月12日 下午10:29:12
 * @decrition 单生产者单消费者模式
 */
public class Single_Producer_Consumer {

	public static void main(String[] args)
	{
		KaoYaResource r = new KaoYaResource();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);
		//生产者线程
		Thread t0 = new Thread(pro);
		//消费者线程
		Thread t2 = new Thread(con);
		//启动线程
		t0.start();
		t2.start();
	}
}
/**
 * @author zejian
 * @time 2016年3月12日 下午11:02:22
 * @decrition 生产者线程
 */
class Producer implements Runnable
{
	private KaoYaResource r;
	Producer(KaoYaResource r)
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
class Consumer implements Runnable
{
	private KaoYaResource r;
	Consumer(KaoYaResource r)
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