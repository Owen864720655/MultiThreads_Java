package com.fanglei.thinking_in_java_book.chapter21_concurrency;

public class MoreBasicThreads {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 5; i++)
			new Thread(new LiftOff(12)).start();

		System.out.println("Waiting for LiftOff!");
	}

}
