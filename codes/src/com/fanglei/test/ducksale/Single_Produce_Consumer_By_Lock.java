package com.fanglei.test.ducksale;

/**
 * Created by Owen on 2017/3/31.
 */
public class Single_Produce_Consumer_By_Lock {

	public static void main(String[] args)
	{
		KaoYaResourceByLock r = new KaoYaResourceByLock();
		ProducerByLocker pro = new ProducerByLocker(r);
		ConsumerByLocker con = new ConsumerByLocker(r);
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
class ProducerByLocker implements Runnable
{
	private KaoYaResourceByLock r;
	ProducerByLocker(KaoYaResourceByLock r)
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
class ConsumerByLocker implements Runnable
{
	private KaoYaResourceByLock r;
	ConsumerByLocker(KaoYaResourceByLock r)
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
