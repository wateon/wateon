package wateon;

public class ConnectionCloserThread implements Runnable {
	@Override
	public void run() {
		// 주기적으로, 모든 유저의 접속 연결을 체크 해준다.
		while (true) {
			WateOn.getInstance().checkAllConnection();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
