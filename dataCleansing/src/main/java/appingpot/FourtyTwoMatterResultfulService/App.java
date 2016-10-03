package appingpot.FourtyTwoMatterResultfulService;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import appingpot.data_cleansing.AppingpotCleansing;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		int sleepSec = 60 * 5; // routine in 5 minutes
		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					AppingpotCleansing appingpotCleansing = new AppingpotCleansing();
					appingpotCleansing.cleansing();
				} catch (Exception e) {
					e.printStackTrace();
					exec.shutdown();
				}
			}
		}, 0, sleepSec, TimeUnit.SECONDS);
	}
}
