package fb.auditTrail.dao;

import fb.auditTrail.model.AuditTrail;

public interface AuditDAO {
	public void insertAuditDetails(int brandId, AuditTrail auditTrail);
}
