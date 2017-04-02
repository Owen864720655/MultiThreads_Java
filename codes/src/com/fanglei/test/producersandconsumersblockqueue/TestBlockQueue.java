//package com.fanglei.test.producersandconsumersblockqueue;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingDeque;
//
///**
// * Created by Owen on 2017/4/2.
// */
//public class TestBlockQueue {
//	int
//}
//
//
///**
// * 资源类
// */
//class Resource {
//	/**
//	 * 阻塞队列的长度
//	 */
//	private int MAX_SIZE = 500000;
//
//	/**
//	 * 定义的阻塞队列
//	 */
//	private BlockingQueue<ResourceObject> blockingQueue = new LinkedBlockingDeque<ResourceObject>(MAX_SIZE);
//
//	/**
//	 * 生产者的个数，通过本变量来检查生产者线程是否销亡
//	 */
//	private int numProducers = 0;
//
//	public Resource() {
//	}
//
//	/**
//	 * 构造函数
//	 *
//	 * @param maxSize      阻塞队列的长度
//	 * @param numProducers 生产者的个数
//	 */
//	public Resource(int maxSize, int numProducers) {
//		this.MAX_SIZE = maxSize;
//		this.numProducers = numProducers;
//		System.out.println("blockingQueue.size(): " + blockingQueue.size());
//	}
//
//	/**
//	 * 生产者生产
//	 *
//	 * @param resourceObject 生产的号码对象
//	 */
//	public void produce(ResourceObject resourceObject) {
//		try {
//			blockingQueue.put(resourceObject);
//			System.out.println("Produce one resource (itemId: " + resourceObject.getItemId()
//					+ ", itemCode: " + resourceObject.getItemCode()
//					+ ", isMatched: " + resourceObject.getItemCode() + ")");
//		} catch (InterruptedException e) {
//			System.out.println("Resource produce() is interrupted!");
//		}
//	}
//
//	/**
//	 * 消费者消费
//	 *
//	 * @return 消费的号码对象
//	 */
//	public ResourceObject consumer() {
//		ResourceObject resourceObject = new ResourceObject();
//
//		try {
//			resourceObject = blockingQueue.take();
//			System.out.println("Produce one resource (itemId: " + resourceObject.getItemId()
//					+ ", itemCode: " + resourceObject.getItemCode()
//					+ ", isMatched: " + resourceObject.getItemCode() + ")");
//		} catch (InterruptedException e) {
//			System.out.println("Resource consumer() is interrupted!");
//		}
//		return resourceObject;
//	}
//
//
//	public void setNumProducers(int numProducers) {
//		this.numProducers = numProducers;
//	}
//
//	public int getNumProducers() {
//		return this.numProducers;
//
//	}
//}
//
//
///**
// * 资源队列中的Object
// */
//class ResourceObject
//{
//
//	/**
//	 * 号码编号
//	 */
//	private	String itemId = "";
//
//	/**
//	 * 号码编码
//	 */
//	private String itemCode = "";
//
//	/**
//	 * 是否通过了模式匹配标志位
//	 */
//	private Boolean isMatched = false;
//
//	/**
//	 * 构造函数
//	 *
//	 * @param itemId 号码编号
//	 * @param itemCode 号码
//	 * @param isMatched 是否成功匹配标志位
//	 */
//	ResourceObject(String itemId, String itemCode, Boolean isMatched)
//	{
//		this.itemId = itemId;
//		this.itemCode = itemCode;
//		this.isMatched = isMatched;
//	}
//
//	/**
//	 * 设置号码编号
//	 * @param itemId 设置的号码编号
//	 */
//	public void setItemId(String itemId)
//	{
//		this.itemId = itemId;
//	}
//
//	/**
//	 * 获取号码编号
//	 * @return 号码编号
//	 */
//	public String getItemId()
//	{
//		return this.itemId;
//	}
//
//	/**
//	 * 设置号码
//	 * @param itemCode 号码
//	 */
//	public void setItemCode(String itemCode)
//	{
//		this.itemCode = itemCode;
//	}
//
//	/**
//	 * 获取号码
//	 * @return 号码
//	 */
//	public String getItemCode()
//	{
//		return this.itemCode;
//	}
//
//	/**
//	 * 设置是否匹配成功标志位
//	 *
//	 * @param isMatched 匹配成功标志位
//	 */
//	public void setIsMatched(Boolean isMatched)
//	{
//		this.isMatched = isMatched;
//	}
//
//	/**
//	 * 获取是否匹配成功标志位
//	 * @return 是否成功标志位
//	 */
//	public Boolean getIsMatched()
//	{
//		return this.getIsMatched();
//	}
//
//}
//
//class Producer implements Runnable
//{
//	private Resource resource;
//	private int maxSize;
//
//	private int iterNum;
//
//	public Producer(Resource resource, int maxSize, int iterNum)
//	{
//		this.resource = resource;
//		this.maxSize = maxSize;
//		this.iterNum = iterNum;
//	}
//
//	private List<ResourceObject> generateTelnums()
//	{
//		List<ResourceObject> results = new ArrayList<ResourceObject>(maxSize);
//		ResourceObject result = new ResourceObject();
//		for (int i = 0; i < maxSize; i++)
//		{	BigDecimal telnumBase = new BigDecimal("18550000000");
//			int telnumDiffInt = new Random().nextInt(10000000);
//			BigDecimal telnumDiff = new BigDecimal(telnumDiffInt);
//			BigDecimal telnum = telnumBase.add(telnumDiff);
//
//			String itemCode = telnum.toString();
//			String itemId = "99999" + itemCode;
//
//			Boolean isMatched = false;
//			if((telnumDiffInt%1000) > 0 && ((telnumDiffInt%1000)%518 == 0))
//			{
//				isMatched = true;
//			}
//
//			result.setItemId(itemId);
//			result.setItemCode(itemCode);
//			result.setIsMatched(isMatched);
//
//			results.add(result);
//		}
//
//		return results;
//	}
//
//	public void run() {
////		try {
////			while ((iterNum--) > 0) {
////				List<ResourceObject> resourceObjects = generateTelnums();
////				for(ResourceObject resourceObject : resourceObjects)
////				{
////					resource.produce(resourceObject);
////				}
////				System.out.println("Producer thread " + Thread.currentThread().getName() + " produces " + resourceObjects.size() + " telnums,");
////
////			}
////		} catch (InterruptedException ex) {
////			System.out.println("Producer INTERRUPTED");
////		}
//		while ((iterNum--) > 0) {
//			List<ResourceObject> resourceObjects = generateTelnums();
//			for (ResourceObject resourceObject : resourceObjects) {
//				resource.produce(resourceObject);
//			}
//			System.out.println("Producer thread " + Thread.currentThread().getName() + " produces " + resourceObjects.size() + " telnums,");
//
//		}
//	}
//
//}
//
//
