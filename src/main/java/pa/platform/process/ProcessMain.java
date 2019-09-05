package pa.platform.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



import pa.platform.core.PaConfiguration;
import pa.platform.targeting.service.TargetingService;



public class ProcessMain {
	
	private static final int HANDLERS = 1;
	private ScheduledExecutorService scheduledThreadPool = null;
	
	private static Logger logger = Logger.getLogger(ProcessMain.class);
	
	
	
	public static void main(String[] args) {
		ProcessMain main = new ProcessMain();
		String conf = System.getProperty("configfile");
		if(conf != null){
			PaConfiguration.getInstance().initilize(conf);
		try {
			main.configureLogging(PaConfiguration.getInstance().getConfiguration("log4j_prop"));
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
		}
		main.startProcesses();
		}else{
			logger.info("Configuration file is not available");
		}
	}

	private void startProcesses() {
		
		scheduledThreadPool = Executors.newScheduledThreadPool(HANDLERS);
		TargetingService targettingService = new TargetingService();
		scheduledThreadPool.schedule(targettingService, 1, TimeUnit.SECONDS);
		try {
			scheduledThreadPool.awaitTermination(1000, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
		} catch (Exception e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
		}
		scheduledThreadPool.shutdown();
        while(!scheduledThreadPool.isTerminated()){
            //wait for all tasks to finish
        }
		
	}
	
	private void configureLogging(String logPropFile) throws FileNotFoundException, IOException {
		 InputStream logStream = new FileInputStream(new File(logPropFile));
		 Properties prop = new Properties();
		if(logStream.available()>0){
			prop.load(logStream);
			PropertyConfigurator.configure(prop);
		}else{
			logStream.close();
			throw new RuntimeException("Log property file cannot be found!");
			
		}
	}

}
