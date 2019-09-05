package pa.platform.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pa.platform.core.DaoManager;
import pa.platform.dao.ReportEventNotificationDao;
import pa.platform.model.ReportEventNotification;

public class ReportEventNotificationDaoImpl implements ReportEventNotificationDao{

	@Override
	public List<ReportEventNotification> getReportEventNotification(int brandId, int eventId, boolean isAdmin) throws SQLException,Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query= null;
		List<ReportEventNotification> reportEventNotificationList = new ArrayList<ReportEventNotification>();
		try{
			query = "select * from PricingAnalytics.dbo.ReportEventNotification where Deleted = 0 and BrandId = ? and EventId = ? and TargetGroup IN (0,1) order by Sequence";
			if(isAdmin){
				query = "select * from PricingAnalytics.dbo.ReportEventNotification where Deleted = 0 and BrandId = ? and EventId = ? and TargetGroup IN (0,2) order by Sequence";
			}
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, brandId);
			preparedStatement.setInt(2, eventId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ReportEventNotification eventNotification = new ReportEventNotification();
				eventNotification.setId(rs.getInt("Id"));
				eventNotification.setActionsId(rs.getInt("ActionsId"));
				eventNotification.setBrandId(rs.getInt("BrandId"));
				eventNotification.setCreated(rs.getDate("Created"));
				eventNotification.setCreatedBy(rs.getString("CreatedBy"));
				eventNotification.setData(rs.getString("Data"));
				eventNotification.setEventId(rs.getInt("EventId"));
				eventNotification.setTemplateId(rs.getInt("TemplateId"));
				eventNotification.setSequence(rs.getInt("Sequence"));
				eventNotification.setDeleted(rs.getBoolean("Deleted"));
				eventNotification.setTargetGroup(rs.getInt("TargetGroup"));
				reportEventNotificationList.add(eventNotification);
				
			}
			
		}catch(Exception ex){
			//
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		return reportEventNotificationList;
	}

}
