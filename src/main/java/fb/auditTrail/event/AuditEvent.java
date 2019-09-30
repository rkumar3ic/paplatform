package fb.auditTrail.event;

import java.io.Serializable;

import pa.platform.event.PaEvent;
import pa.platform.event.PaNotificationEvent;
import fb.auditTrail.model.AuditTrail;

public class AuditEvent extends PaEvent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String action;
	private AuditTrail auditTrail;
	
	public AuditEvent() {}
	
	public AuditEvent(int brandId, AuditTrail auditTrail) {
		this.brandId = brandId;
		this.auditTrail = auditTrail;
	}

	public AuditEvent(String action, int brandId, AuditTrail auditTrail) {
		this.action = action;
		this.brandId = brandId;
		this.auditTrail = auditTrail;
	}

	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}
	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	

}
