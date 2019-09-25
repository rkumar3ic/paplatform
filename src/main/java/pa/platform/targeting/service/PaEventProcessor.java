package pa.platform.targeting.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import fb.auditTrail.event.AuditEvent;
import fb.auditTrail.service.AuditService;
import fb.auditTrail.service.impl.AuditServiceImpl;
import pa.platform.core.enums.MergeCodes;
import pa.platform.core.enums.ReportStageLKP;
import pa.platform.event.PaEvent;
import pa.platform.event.PaNotificationEvent;
import pa.platform.event.ReportCommentEvent;
import pa.platform.event.ReportRequestEvent;
import pa.platform.event.ReportStatusEvent;
import pa.platform.model.Notification;
import pa.platform.notification.NotificationEngine;

public class PaEventProcessor implements Runnable {
	
	private static Logger logger = Logger.getLogger(PaEventProcessor.class);
	
	private PaNotificationEvent event;
	private PaEvent paEvent;
	
	public PaEventProcessor(PaNotificationEvent event) {
		this.event = event;
	}

	public PaEventProcessor(PaEvent paEvent) {
		this.paEvent = paEvent;
	}


	@Override
	public void run() {
		Notification notif = new Notification();
		
		Map<String,String> mergeCodesmap = new HashMap<String,String>();
		notif.setMergeCodesMap(mergeCodesmap);
		
		notif.setBrandId(event.getBrandId());
		notif.setReportId((event.getReportId() != null ? event.getReportId() : 0));
		notif.setReportType((event.getReportId() != null ? event.getReportId() : 0));
		notif.setUserName(event.getUserName());
		notif.setUserId((event.getUserId() != null ? event.getUserId() : 0));
		
		if(event instanceof ReportRequestEvent){
			logger.info("Report Request Event...");
			ReportRequestEvent requestEvent = (ReportRequestEvent) event;
			notif.setCurrentReportStage(requestEvent.getCurrentReportStage());
			notif.setEventId(1);
			if(null != requestEvent.getRequestedOnBehalf()){
				notif.setEventId(6);
			}
			notif.getMergeCodesMap().put(MergeCodes.NEWSTATUS.description(), ReportStageLKP.getReportStage(notif.getCurrentReportStage()).replaceAll("_", " "));
			
			
		}else if(event instanceof ReportCommentEvent){
			logger.info("Report Comment Event...");
			ReportCommentEvent commentEvent = (ReportCommentEvent) event;
			notif.setCurrentReportStage(commentEvent.getCurrentReportStage());
			notif.setEventId(3);
			notif.getMergeCodesMap().put(MergeCodes.NEWSTATUS.description(), ReportStageLKP.getReportStage(notif.getCurrentReportStage()).replaceAll("_", " "));
			
			
		}else if(event instanceof ReportStatusEvent){
			logger.info("Report Status Event...");
			ReportStatusEvent statusEvent = (ReportStatusEvent) event;
			notif.setCurrentReportStage(statusEvent.getCurrentReportStage());
			notif.setNewReportStage(statusEvent.getNewReportStage());
			if(statusEvent.getNewReportStage() == 7){
				notif.setEventId(4);
			}else if(statusEvent.getNewReportStage() == 4){
				notif.setEventId(5);
			}else{
				notif.setEventId(2);
			}
			notif.setEventId(2);
			notif.getMergeCodesMap().put(MergeCodes.OLDSTATUS.description(), ReportStageLKP.getReportStage(notif.getCurrentReportStage()).replaceAll("_", " "));
			notif.getMergeCodesMap().put(MergeCodes.NEWSTATUS.description(), ReportStageLKP.getReportStage(notif.getNewReportStage()).replaceAll("_", " "));
		}
			triggerNotifications(notif);

	}

	private void triggerNotifications(Notification notif) {
		NotificationEngine notifEngine = new NotificationEngine(notif);
		notifEngine.sendNotifications();
		
	}

}
