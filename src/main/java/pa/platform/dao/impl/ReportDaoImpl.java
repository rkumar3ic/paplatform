package pa.platform.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pa.platform.core.DaoManager;
import pa.platform.dao.ReportDao;
import pa.platform.model.Notification;
import pa.platform.model.Report;

public class ReportDaoImpl implements ReportDao{

	@Override
	public Report getReportById(int reportId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Report report = null;
		String query="select * from PricingAnalytics.dbo.Report where Id = ?";
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, reportId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				report = new Report();
				report.setName(rs.getString("Name"));
				report.setCreatedBy(rs.getString("CreatedBy"));
				report.setStage(rs.getInt("Stage"));
				report.setType(rs.getInt("Type"));
				report.setRequestedOnBehalf(rs.getString("RequestedOnBehalf"));
			}
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(), ex.fillInStackTrace());
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		
		return report;
		
	}
	
	public void updateNotification(Notification notif){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query="UPDATE PricingAnalytics.dbo.Report SET Stage=?,LastUpdated=GETDATE(),LastUpdatedBy=? where Id = ? and BrandId = ? ";
		
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, notif.getNewReportStage());
			//preparedStatement.setDate(2, java.sql.Date.valueOf(Instant.now().toString()));
			preparedStatement.setString(2, notif.getUserName());
			preparedStatement.setInt(3, notif.getReportId());
			preparedStatement.setInt(4, notif.getBrandId());
			int rowsEffected = preparedStatement.executeUpdate();
			if(rowsEffected > 0){
				//
			}
		}catch(Exception ex){
			
		}finally {
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
	}


}
