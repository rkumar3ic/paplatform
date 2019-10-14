package fb.auditTrail.processor;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import fb.auditTrail.event.AuditEvent;
import fb.auditTrail.service.AuditService;
import fb.auditTrail.service.impl.AuditServiceImpl;
import fb.auditTrail.util.AuditConstants;
import pa.platform.event.PaEvent;

public class AuditEventProcessor implements Runnable {
	
	private static Logger logger = Logger.getLogger(AuditEventProcessor.class);
	
	private PaEvent paEvent;
	private String correlationId;
	
	public AuditEventProcessor(PaEvent paEvent) {
		this.paEvent = paEvent;
	}


	@Override
	public void run() {
		AuditEvent auditEvent = (AuditEvent) paEvent;
		configureCorrelationId(auditEvent.getAuditTrail().getAuditId());
		logger.debug("Audit Event...");
		AuditService auditService = new AuditServiceImpl();
		auditService.insertAuditDetails(paEvent.getBrandId(), auditEvent.getAuditTrail());
		cleanupCorrelationId();
	}
	
	private void configureCorrelationId(String auditId){
		correlationId = (String) MDC.get(AuditConstants.CORRELATION_ID_LOG_VAR_NAME);
		if (correlationId == null) {
			correlationId = auditId;
			logger.debug("No correlationId found. Assigned : " + correlationId);
		} else {
			logger.debug("Found correlationId : " + correlationId);
		}
		MDC.put(AuditConstants.CORRELATION_ID_LOG_VAR_NAME, correlationId);
	}

	private void cleanupCorrelationId(){
		logger.debug("Removing correlationId : " + correlationId);
		MDC.remove(AuditConstants.CORRELATION_ID_LOG_VAR_NAME);
	}

}
