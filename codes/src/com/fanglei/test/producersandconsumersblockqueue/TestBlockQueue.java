package com.fanglei.test.producersandconsumersblockqueue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Owen on 2017/4/2.
 */
public class TestBlockQueue {
	public static void main(String[] arg)
	{

		double timeStart = System.currentTimeMillis();

		int maxSizeBlockingQueue = 50;
		int numProducers = 2;
		Resource resource = new Resource(maxSizeBlockingQueue, numProducers);

		int numThreadProducers = 2;
		ExecutorService executorServiceProduers = Executors.newFixedThreadPool(numThreadProducers);

		int maxSizeTelnumsProducers = 10;
		for(int i = 0; i < numThreadProducers; i ++)
		{
			int taskCountProducerBase = 10;
			int taskCountProducerRandom = 10;
			int taskCountProducer = new Random().nextInt(taskCountProducerRandom) + taskCountProducerBase;
			executorServiceProduers.submit(new Producer(resource, maxSizeTelnumsProducers, taskCountProducer));
		}

		int numThreadConsumers = 1;
		int sizeConsumeTelnums = 10;
		ExecutorService executorServiceConsumers = Executors.newFixedThreadPool(numThreadConsumers);
		for (int i = 0; i < numThreadProducers; i++)
		{
			executorServiceConsumers.submit(new Consumer(resource, sizeConsumeTelnums));
		}

		while(true)
		{
			if(executorServiceProduers.isTerminated() && executorServiceProduers.isTerminated())
			{
				executorServiceProduers.shutdown();
				executorServiceConsumers.shutdown();
				break;
			}
		}

		double timeEnd = System.currentTimeMillis();
		System.out.println("Time cost: " + (timeEnd - timeStart) + " ms");

	}
}



/**
 * 资源类
 */
class Resource {
	/**
	 * 阻塞队列的长度
	 */
	private int maxSizeBlockingQueue = 500000;

	/**
	 * 定义的阻塞队列
	 */
	private BlockingQueue<ResourceObject> blockingQueue;

	/**
	 * 生产者的个数，通过本变量来检查生产者线程是否销亡
	 */
	private int numProducers = 0;

	/**
	 * 构造函数
	 *
	 * @param maxSizeBlockingQueue      阻塞队列的长度
	 * @param numProducers 生产者的个数
	 */
	public Resource(int maxSizeBlockingQueue, int numProducers) {
		this.maxSizeBlockingQueue = maxSizeBlockingQueue;
		this.blockingQueue= new LinkedBlockingDeque<ResourceObject>(maxSizeBlockingQueue);
		this.numProducers = numProducers;
		System.out.println("blockingQueue.size(): " + blockingQueue.size());
	}

	/**
	 * 定义toString()函数
	 *
	 * @return 资源类的信息
	 */
	public String toString()
	{
		return "resource.maxSizeBlcokingQueue-" + maxSizeBlockingQueue +
				", numProducers-" + numProducers + ", blockingQueue.size()-" + blockingQueue.size() + ".";
	}
	/**
	 * 生产者生产
	 *
	 * @param resourceObject 生产的号码对象
	 */
	public void produce(ResourceObject resourceObject) {
		try {
			blockingQueue.put(resourceObject);
			System.out.println("Producer " + Thread.currentThread().getName() + " produces one resource (itemId: " + resourceObject.getItemId()
					+ ", itemCode: " + resourceObject.getItemCode()
					+ ", isMatched: " + resourceObject.getIsMatched() + ")");
		} catch (InterruptedException e) {
			System.out.println("Resource produce() is interrupted!");
		}
	}

	/**
	 * 简单的号码模式匹配: 只匹配尾号为518的号码
	 * @param params 号码资源列表
	 * @return 符合模式的号码资源列表
	 */
	public List<ResourceObject> matchPattern(List<ResourceObject> params)
	{
		List<ResourceObject> results = new ArrayList<>();
		ResourceObject result = new ResourceObject();

		for (ResourceObject param : params)
		{
			BigDecimal telnum = new BigDecimal(param.getItemCode());
			BigDecimal pattern = new BigDecimal("518");
			BigDecimal patternTail = new BigDecimal("1000");
			patternTail = telnum.divideAndRemainder(patternTail)[1];
			if(patternTail.compareTo(BigDecimal.ZERO) >0 && patternTail.divideAndRemainder(pattern)[1].compareTo(BigDecimal.ZERO) == 0)
			{
				param.setIsMatched(true);
				results.add(param);
			}
		}

		return results;
	}


	/**
	 * 消费者消费
	 *
	 * @return 消费的号码对象
	 */
	public ResourceObject consumer() {
		ResourceObject resourceObject = new ResourceObject();

		try {
			resourceObject = blockingQueue.take();
//			System.out.println("Produce one resource (itemId: " + resourceObject.getItemId()
//					+ ", itemCode: " + resourceObject.getItemCode()
//					+ ", isMatched: " + resourceObject.getItemCode() + ")");
		} catch (InterruptedException e) {
			System.out.println("Resource consumer() is interrupted!");
		}
		return resourceObject;
	}

	/**
	 * 一次消费多个号码，并进行模式匹配
	 *
	 * @param sizeConsume 消费的号码个数
	 * @return 资源列表
	 */
	public List<ResourceObject> consumerManyAndMatchPattern(int sizeConsume)
	{
		List<ResourceObject> results = new ArrayList<>();
		for (int i = 0; i < sizeConsume; i++)
		{
			results.add(consumer());
		}

		return matchPattern(results);
	}
	/**
	 * 设置生产者线程数量
	 *
	 * @param numProducers 待设置的生产者线程数量
	 */
	public void setNumProducers(int numProducers) {
		this.numProducers = numProducers;
	}

	/**
	 * 获取生产者线程数量
	 * @return 生产者线程数量
	 */
	public int getNumProducers() {
		return this.numProducers;

	}

	/**
	 * 获取阻塞队列的长度
	 *
	 * @return 阻塞队列的长度
	 */
	public int getBlockingQueueSize() {
		return blockingQueue.size();
	}
}



/**
 * 资源队列中的Object
 */
class ResourceObject
{
	/**
	 * 号码编号
	 */
	private	String itemId = "";

	/**
	 * 号码编码
	 */
	private String itemCode = "";

	/**
	 * 是否通过了模式匹配标志位
	 */
	private Boolean isMatched = false;

	/**
	 * 默认构造函数
	 */
	ResourceObject() {}

	/**
	 * 构造函数
	 *
	 * @param itemId 号码编号
	 * @param itemCode 号码
	 * @param isMatched 是否成功匹配标志位
	 */
	ResourceObject(String itemId, String itemCode, Boolean isMatched)
	{
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.isMatched = isMatched;
	}

	/**
	 * 设置号码编号
	 * @param itemId 设置的号码编号
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * 获取号码编号
	 * @return 号码编号
	 */
	public String getItemId()
	{
		return this.itemId;
	}

	/**
	 * 设置号码
	 * @param itemCode 号码
	 */
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}

	/**
	 * 获取号码
	 * @return 号码
	 */
	public String getItemCode()
	{
		return this.itemCode;
	}

	/**
	 * 设置是否匹配成功标志位
	 *
	 * @param isMatched 匹配成功标志位
	 */
	public void setIsMatched(Boolean isMatched)
	{
		this.isMatched = isMatched;
	}

	/**
	 * 获取是否匹配成功标志位
	 * @return 是否成功标志位
	 */
	public Boolean getIsMatched()
	{
		return this.getIsMatched();
	}
}


/**
 * 生产者类
 */
class Producer implements Runnable
{
	/**
	 * 与消费者共享资源类成员
	 */
	private Resource resource;

	/**
	 * 每次生产的号码数量
	 */
	private int maxSizeTelnums = 10;

	/**
	 * 总的生产次数
	 */
	private int taskCount = 0;

	/**
	 * 构造函数
	 *
	 * @param resource 资源
	 * @param maxSizeTelnums 每次生产的号码数量
	 * @param taskCount 生产次数上限
	 */
	public Producer(Resource resource, int maxSizeTelnums, int taskCount)
	{
		this.resource = resource;
		this.maxSizeTelnums = maxSizeTelnums;
		this.taskCount = taskCount;
	}

	public String toString()
	{
		return "Producer resource-" + resource.toString() + ", maxSizeTelnums-" + maxSizeTelnums + ", taskCount-" + taskCount + ".";
	}
	/**
	 * 生产号码:使用FOR循环来生产1855开头的后7位随机生产的号码
	 *
	 * @return 资源类的对象的列表
	 */
	private List<ResourceObject> generateTelnums()
	{
		List<ResourceObject> results = new ArrayList<ResourceObject>(maxSizeTelnums);
		ResourceObject result = new ResourceObject();

		for (int i = 0; i < maxSizeTelnums; i++)
		{	BigDecimal telnumBase = new BigDecimal("18550000000");
			int telnumDiffInt = new Random().nextInt(10000000);
			BigDecimal telnumDiff = new BigDecimal(telnumDiffInt);
			BigDecimal telnum = telnumBase.add(telnumDiff);

			String itemCode = telnum.toString();
			String itemId = "99999" + itemCode;
			Boolean isMatched = false;
//			if((telnumDiffInt%1000) > 0 && ((telnumDiffInt%1000)%518 == 0))
//			{
//				isMatched = true;
//			}

			result.setItemId(itemId);
			result.setItemCode(itemCode);
			result.setIsMatched(isMatched);

			results.add(result);
		}

		return results;
	}

	/**
	 * 线程的RUN()--执行taskCount次资源生产
	 */
	public void run() {
//		try {
//			while ((taskCount--) > 0) {
//				List<ResourceObject> resourceObjects = generateTelnums();
//				for(ResourceObject resourceObject : resourceObjects)
//				{
//					resource.produce(resourceObject);
//				}
//				System.out.println("Producer thread " + Thread.currentThread().getName() + " produces " + resourceObjects.size() + " telnums,");
//
//			}
//		} catch (InterruptedException ex) {
//			System.out.println("Producer INTERRUPTED");
//		}
		while ((taskCount--) > 0) {
			List<ResourceObject> resourceObjects = generateTelnums();

			for (ResourceObject resourceObject : resourceObjects) {
				resource.produce(resourceObject);
			}
//			System.out.println("Producer thread " + Thread.currentThread().getName() + " produces " + resourceObjects.size() + " telnums,");
			System.out.println("Producer thread " + Thread.currentThread().getName() + " produces " + resourceObjects.size() + " telnums,");
			System.out.println("Producer thread " + Thread.currentThread().getName() + "blockingQueue.size(): " + resource.getBlockingQueueSize());
		}

		this.resource.setNumProducers(this.resource.getNumProducers()-1);
	}

}

/**
 * 消费者类
 */
class Consumer implements Runnable
{
	/**
	 * 与生产者共享的资源类成员
	 */
	private Resource resource;

	/**
	 * 每次消费的号码个数
	 */
	private int sizeConsumeTelnums = 100000;

	/**
	 * 构造函数
	 */
	public Consumer(Resource resource, int sizeConsumeTelnums)
	{
		this.resource = resource;
		this.sizeConsumeTelnums = sizeConsumeTelnums;
		System.out.println("Consumer Thread " + Thread.currentThread().getName() + " Constructor, sizeConsumeTelnums: " + this.sizeConsumeTelnums);
	}


	/**
	 * run函数
	 */
	@Override
	public void run() {

		while (true) {
			if (resource.getNumProducers() > 0) {
				if (resource.getBlockingQueueSize() >= sizeConsumeTelnums) {
					List<ResourceObject> results = resource.consumerManyAndMatchPattern(sizeConsumeTelnums);
					for (ResourceObject result : results) {
						System.out.println("Producer Thread " + Thread.currentThread().getName() + " produces one resource (itemId: " + result.getItemId()
								+ ", itemCode: " + result.getItemCode()
								+ ", isMatched: " + result.getItemCode() + ")");
					}
				}
			}
			else {
				List<ResourceObject> results = resource.consumerManyAndMatchPattern(resource.getBlockingQueueSize());
				for (ResourceObject result : results) {
					System.out.println("Producer Thread " + Thread.currentThread().getName() + " produces one resource (itemId: " + result.getItemId()
							+ ", itemCode: " + result.getItemCode()
							+ ", isMatched: " + result.getItemCode() + ")");

				}
			}

			if(resource.getNumProducers() == 0 && resource.getBlockingQueueSize() == 0)
			{
				break;
			}

		}
	}

}



