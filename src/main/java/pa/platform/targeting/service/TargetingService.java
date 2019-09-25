package pa.platform.targeting.service;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import fb.auditTrail.event.AuditEvent;
import fb.auditTrail.processor.AuditEventProcessor;
import pa.platform.core.DaoManager;
import pa.platform.core.PaConfiguration;
import pa.platform.core.PaConstants;
import pa.platform.event.PaEvent;
import pa.platform.event.PaNotificationEvent;
import pa.platform.process.PaService;
import pa.platform.queue.impl.QueuePublisherImpl;




public class TargetingService extends PaService{
	
	private static Logger logger = Logger.getLogger(TargetingService.class);
	
	public static final Map<Integer, ExecutorService> paExecutorMap = new HashMap<Integer, ExecutorService>();
	public static final List<String> paQueueNames = new ArrayList<String>();
	
	
	
	List<QueuePublisherImpl> queues = new ArrayList<QueuePublisherImpl>();
	List<QueuePublisherImpl> processedQueues = new ArrayList<QueuePublisherImpl>();

	public static ExecutorService targettingPool;
	
	@Override
	public void runService() {
		processedQueues= new ArrayList<QueuePublisherImpl>();
		int numberOfQueuesToProcess=queues.size();
		logger.debug("Queues Size ="+ numberOfQueuesToProcess);
		for(QueuePublisherImpl queue : queues){
			try {
				
				List<PaEvent> events = queue.getEventsFromQueue(numberOfQueuesToProcess);

				if (events.size() > 0) {
					logger.info("Reading " + events.size() + " Events from queue!!");
					for (PaEvent event : events) {
						if (event != null) {
							if (event instanceof PaNotificationEvent) {
								logger.debug("Processing Loyalty Event Now ...");
								paExecutorMap.get(event.getBrandId()).submit(new PaEventProcessor(event));
							}else if(event instanceof AuditEvent){
								logger.debug("Processing Audit Event Now ...");
								paExecutorMap.get(event.getBrandId()).submit(new AuditEventProcessor(event));
							}
							
						}
					}
				}
			} catch (Throwable ex) {
				logger.error(ex.getMessage(), ex.fillInStackTrace());
				logger.debug(ExceptionUtils.getStackTrace(ex));
			}
		}
		
	
	}

	@Override
	public void startService() {
		try {
			//get queuenames brandwise and their corresponding thread sizes
			fetchPaQueuesFromDB();		
	
			//String[] queueNames = {"riteesh-1036"}; //For running in local
			//paExecutorMap.put(1036, Executors.newFixedThreadPool(10)); //For running in local
			String[] queueNames = (String[]) paQueueNames.toArray(new String[paQueueNames.size()]);

			for(String queueName : queueNames){
				if(queueName != null && queueName.trim().length() > 0){
					QueuePublisherImpl queue = new QueuePublisherImpl();
					queues.add(queue);
					String queueurl = queue.createQueue(queueName);
					logger.info("Servicing queue: " + queueurl);
				
					/*queue.sendEventToQueue("{\"action\":\"reportrequestevent\",\"brandid\":\"1036\",\"reportid\":\"256\",\"currentreportstage\":\"6\",\"reporttype\":\"3\",\"userid\":\"39908\",\"username\":\"abhinavAU\"}");
					queue.sendEventToQueue("{\"action\":\"reportstatusevent\",\"brandid\":\"1036\",\"reportid\":\"252\",\"currentreportstage\":\"1\",\"reporttype\":\"3\",\"userid\":\"39908\",\"username\":\"abhinavAU\",\"newreportstage\":\"2\"}");
					queue.sendEventToQueue("{\"action\":\"reportstatusevent\",\"brandid\":\"1036\",\"reportid\":\"252\",\"currentreportstage\":\"1\",\"reporttype\":\"3\",\"userid\":\"39908\",\"username\":\"abhinavAU\",\"newreportstage\":\"2\"}");
				*/
				}
			}
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
		}
	}
	
	
	private void fetchPaQueuesFromDB(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query="SELECT BrandId,NumberOfThreads,QueueName FROM PricingAnalytics.dbo.Queues where Deleted = 0";
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			logger.info(preparedStatement.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer brandId = rs.getInt("BrandId");
				Integer noOfThreads = rs.getInt("NumberOfThreads");
				String queueName = "paqueue_notification_"+brandId;
				if(!paExecutorMap.containsKey(queueName)){
					paExecutorMap.put(brandId, Executors.newFixedThreadPool(noOfThreads));
				}
				paQueueNames.add(rs.getString("QueueName"));
			}
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(), ex.fillInStackTrace());
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
	}

	
	@Override
	public void stopService() {
		try {
			targettingPool.awaitTermination(1000, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("TargettingService was interupted: ",e.fillInStackTrace());
		}
		targettingPool.shutdown();
		logger.info("TargetingService is stopped...");

	}
	
	

}
