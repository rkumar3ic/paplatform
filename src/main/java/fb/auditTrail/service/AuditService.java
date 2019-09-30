package fb.auditTrail.service;

import fb.auditTrail.model.AuditTrail;

public interface AuditService {
	
	public void insertAuditDetails(int brandId, AuditTrail auditTrail);

}
