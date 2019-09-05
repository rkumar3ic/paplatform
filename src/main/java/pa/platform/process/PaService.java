package pa.platform.process;

import org.apache.log4j.Logger;

public abstract class PaService implements Runnable{
	
	private boolean isServiceAlive = true;
	
	private static Logger logger = Logger.getLogger(PaService.class);
	
	private void initService() {
		isServiceAlive = true;
		startService();
	}
	
	@Override
	public void run() {
		initService();
		while(isServiceAlive){
		runService();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
		}
		}
		haltService();
	}
	public abstract void startService();
	public abstract void runService();
	public abstract void stopService();

	private void haltService() {
		isServiceAlive = false;
		stopService();
	}

}
