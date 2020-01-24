package fvarrui.filecast.net.packets;

public class ThreadUtils {
	
	public static Thread start(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.start();
		return thread;
	}
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// do nothing
		}
	}

}
