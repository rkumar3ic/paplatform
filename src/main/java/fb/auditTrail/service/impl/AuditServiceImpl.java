package fb.auditTrail.service.impl;

import fb.auditTrail.dao.AuditDAO;
import fb.auditTrail.dao.impl.AuditDAOImpl;
import fb.auditTrail.model.AuditTrail;
import fb.auditTrail.service.AuditService;

public class AuditServiceImpl implements AuditService {

	private AuditDAO auditDAO;
	
	public AuditServiceImpl() {
		this.auditDAO = new AuditDAOImpl();
	}

	public void insertAuditDetails(int brandId, AuditTrail auditTrail) {
		auditDAO.insertAuditDetails(brandId, auditTrail);
	}

}
