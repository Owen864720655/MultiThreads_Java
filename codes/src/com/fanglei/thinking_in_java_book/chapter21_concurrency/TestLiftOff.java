package com.fanglei.thinking_in_java_book.chapter21_concurrency;

import com.sun.org.apache.xpath.internal.Arg;

import java.util.concurrent.TimeUnit;

/**
 * Demonstration of the Runnable interface
 * Created by Owen on 2017/4/2.
 */
public class TestLiftOff 
{
	public static void main(String[] arg)
	{
		LiftOff liftOff = new LiftOff(50);
		liftOff.run();
	}
}


