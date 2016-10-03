package appingpot.data_cleansing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleExecTest {
	
	public static void main(String[] args) {
		// ���ణ�� ����(300��)
		int sleepSec = 60*5; // ������ ���̴� raw applog�� normalized_app_log�� 1��*5=5��
		// �ð� ��� ����
		//final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		// �ֱ����� �۾��� ����
		
		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					AppingpotCleansing appingpotCleansing=new AppingpotCleansing();
					appingpotCleansing.cleansing();
				} catch (Exception e) {
					e.printStackTrace();
					// ���� �߻��� Executor�� ������Ų��
					exec.shutdown();
				}
			}
		}, 0, sleepSec, TimeUnit.SECONDS);
	}

}
