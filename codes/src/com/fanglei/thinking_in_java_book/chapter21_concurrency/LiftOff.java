package com.fanglei.thinking_in_java_book.chapter21_concurrency;

/**
 * Created by Owen on 2017/4/2.
 */

import javax.lang.model.element.NestingKind;

/**
 * LiftOff类
 */
public class LiftOff implements Runnable {
	/**
	 * 计数器上限
	 */
	protected int countDown = 10;

	/**
	 * 区分LiftOff类的任务计数器
	 */
	private static int taskCount = 0;

	/**
	 * 区分LiftOff类的任务的编号
	 */
	private final int id = taskCount++;

	/**
	 * 默认构造函数
	 */
	public LiftOff() {
	}

	/**
	 * 构造函数
	 *
	 * @param countDown 发射器的计数上限
	 */
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	/**
	 * 打印结果
	 *
	 * @return 字符串打印结果
	 */
	public String status() {
		return "#" + id + "(" + (countDown > 0 ? String.valueOf(countDown) : "LiftOff!") + "), ";
	}

	/**
	 * run()函数
	 */
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			Thread.yield();
		}

	}
}