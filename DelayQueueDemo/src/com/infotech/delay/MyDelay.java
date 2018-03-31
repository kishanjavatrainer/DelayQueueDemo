package com.infotech.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

//Delayed interface implementing class
public class MyDelay implements Delayed {
	private String item;
	private long expireTime;

	public MyDelay(String item, long delay) {
		this.item = item;
		// Expiretime is currenttime+delay, so if delay of 2 sec is required
		// expiration from queue will happen after  currenttime + 2 sec
		this.expireTime = System.currentTimeMillis() + delay;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = expireTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
			return -1;
		}
		if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "MyDelay [item=" + item + ", expireTime=" + expireTime + "]";
	}
}
