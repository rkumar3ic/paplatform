package fb.auditTrail.processor;

import org.apache.log4j.Logger;

import fb.auditTrail.event.AuditEvent;
import fb.auditTrail.service.AuditService;
import fb.auditTrail.service.impl.AuditServiceImpl;
import pa.platform.event.PaEvent;

public class AuditEventProcessor implements Runnable {
	
	private static Logger logger = Logger.getLogger(AuditEventProcessor.class);
	
	private PaEvent paEvent;
	
	public AuditEventProcessor(PaEvent paEvent) {
		this.paEvent = paEvent;
	}


	@Override
	public void run() {
		logger.info("Audit Event...");
		AuditService auditService = new AuditServiceImpl();
		AuditEvent auditEvent = (AuditEvent) paEvent;
		auditService.insertAuditDetails(paEvent.getBrandId(), auditEvent.getAuditTrail());

	}


}
