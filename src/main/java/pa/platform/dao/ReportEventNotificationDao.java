package pa.platform.dao;

import java.sql.SQLException;
import java.util.List;

import pa.platform.model.ReportEventNotification;

public interface ReportEventNotificationDao {

	public List<ReportEventNotification> getReportEventNotification(int brandId,int eventId,boolean isAdmin) throws SQLException,Exception;
}
