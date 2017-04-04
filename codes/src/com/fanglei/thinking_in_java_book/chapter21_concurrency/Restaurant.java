package com.fanglei.thinking_in_java_book.chapter21_concurrency;

import com.fanglei.test.ThreadByExtendThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Owen on 2017/4/4.
 */

/**
 * 餐厅的菜类
 */
class Meal
{
	private final int orderNum;

	public Meal(int orderNum)
	{
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal{" +
				"orderNum=" + orderNum +
				'}';
	}
}


/**
 * 服务员类
 */
class WaitPerson implements Runnable
{
	private Restaurant restaurant;

	public WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void run()
	{
		try {
			while(!Thread.interrupted())
			{
				synchronized (this)
				{
					while(restaurant.meal == null)
					{
						wait();
					}
				}
				System.out.println("Waitperson got " + restaurant.meal);

				synchronized (restaurant.chef)
				{
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}

			}

		} catch (InterruptedException e)
		{
			System.out.println("Waitperson interrupted.");
		}
	}
}


/**
 * 厨师类
 */
class Chef implements Runnable
{
	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted())
			{
				synchronized (this) {
					while (restaurant.meal != null) {
						wait();
					}

				}
				if(++count == 10)
				{
					System.out.println("Out of food, closing!");
					restaurant.exec.shutdownNow();
				}
				System.out.println("Order up! ");

				synchronized (restaurant.waitPerson)
				{
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}

				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e)
		{
			System.out.print("Chef interrupted. ");
		}
	}
}


/**
 * 餐馆类
 */
public class Restaurant
{
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	public Restaurant()
	{
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String [] arg)
	{
		new Restaurant();
	}

}



