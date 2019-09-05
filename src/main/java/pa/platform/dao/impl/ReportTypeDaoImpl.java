package pa.platform.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pa.platform.core.DaoManager;
import pa.platform.dao.ReportTypeDao;
import pa.platform.model.ReportType;

public class ReportTypeDaoImpl implements ReportTypeDao{

	@Override
	public ReportType getReportType(int reportTypeId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ReportType reportType = null;
		String query="select * from PricingAnalytics.dbo.ReportType where Id = ?";
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, reportTypeId);
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				reportType = new ReportType();
				reportType.setId(rs.getInt("Id"));
				reportType.setDescription(rs.getString("Description"));
			}
		}catch(Exception ex){
			
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		return reportType;
	}

}
