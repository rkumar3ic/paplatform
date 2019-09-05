package pa.platform.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import pa.platform.core.DaoManager;
import pa.platform.dao.NotificationDao;
import pa.platform.model.Notification;

public class NotificationDaoImpl implements NotificationDao{

	private static Logger logger = Logger.getLogger(NotificationDaoImpl.class);
	@Override
	public void saveNotification(Notification notif) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//ResultSet rs = null;
		String createdBy = null;
		String query="INSERT INTO PricingAnalytics.dbo.Notification(ReportId,EventId,BrandId,UserName,NotificationText,IsReviewed,Role,CreatedDate,ReportStage) VALUES (?,?,?,?,?,?,?,GEtDATE(),?)";
		try{
			connection = DaoManager.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, notif.getReportId());
			preparedStatement.setInt(2, notif.getEventId());
			preparedStatement.setInt(3, notif.getBrandId());
			preparedStatement.setString(4, notif.getUserName());
			preparedStatement.setString(5, notif.getNotifcationText());
			preparedStatement.setInt(6, 0);
			preparedStatement.setString(7, notif.getRole());
			preparedStatement.setInt(8, notif.getNewReportStage());
			
			int rowsEffected = preparedStatement.executeUpdate();
			
			if(rowsEffected > 0){
				logger.info("Report Updated Successfully");
			}
		}catch(Exception ex){
			
		}finally {
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		
	}
	
	
}
