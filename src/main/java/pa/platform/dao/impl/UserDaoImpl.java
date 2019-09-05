package pa.platform.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import pa.platform.core.DaoManager;
import pa.platform.dao.UserDao;
import pa.platform.model.UserDetails;

public class UserDaoImpl implements UserDao{

	@Override
	public UserDetails getUserDetailsByUserId(int userId)
			throws SQLException, Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query="select US.UserName,US.FirstName,US.LastName,US.Title,US.Phone,US.Email,US.ClientId,US.TimeZone,RU.RoleId From UserManagement.dbo.RolesForUser as RU "
				+ " JOIN UserManagement.dbo.[User] as US ON US.UserId=RU.UserId where US.UserId = ?";
		UserDetails userDetails = null;	
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,userId);
			//logger.info(preparedStatement.toString());
			rs = preparedStatement.executeQuery();
			
			List<Integer> roles = new ArrayList<Integer>();
			while (rs.next()) {
				userDetails = new UserDetails();
				userDetails.setClientId(rs.getInt("ClientId"));
				userDetails.setEmail(rs.getString("Email"));
				userDetails.setFirstName(rs.getString("FirstName"));
				userDetails.setLastName(rs.getString("LastName"));
				userDetails.setPhone(rs.getString("Phone"));
				userDetails.setTimeZone(rs.getString("TimeZone"));
				userDetails.setTitle(rs.getString("Title"));
				roles.add(rs.getInt("RoleId"));
				userDetails.setUserName(rs.getString("Username"));
			}
			userDetails.setRoleId(roles);
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(), ex.fillInStackTrace());
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		return userDetails;
	}

	@Override
	public UserDetails getUserDetailsByUserName(String userName)throws SQLException, Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query="select UserName,FirstName,LastName,Title,Phone,Email,ClientId,TimeZone from User where UserName = ?";
		UserDetails userDetails = null;	
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				userDetails = new UserDetails();
				userDetails.setClientId(rs.getInt("ClientId"));
				userDetails.setEmail(rs.getString("Email"));
				userDetails.setFirstName(rs.getString("FirstName"));
				userDetails.setLastName(rs.getString("LastName"));
				userDetails.setPhone(rs.getString("Phone"));
				userDetails.setTimeZone(rs.getString("TimeZone"));
				userDetails.setTitle(rs.getString("Title"));
				userDetails.setUserName(rs.getString("Username"));
			}
			
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(), ex.fillInStackTrace());
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		
		return userDetails;
	}
	
	
	@Override
	public List<UserDetails> getAdminUsersByBrandId(int brandId) throws SQLException,Exception{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query="select * from UserManageMent.dbo.[User] u JOIN UserManageMent.dbo.RolesForUser r on r.UserId=u.UserId JOIN StoreManagement.dbo.BrandsForUser su ON su.UserId=u.UserId and r.UserId=su.UserId where r.roleId= 1001 and su.BrandID = ?";
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, brandId);
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				UserDetails userDetails = new UserDetails();
				userDetails.setClientId(rs.getInt("ClientId"));
				userDetails.setEmail(rs.getString("Email"));
				userDetails.setFirstName(rs.getString("FirstName"));
				userDetails.setLastName(rs.getString("LastName"));
				userDetails.setUserName(rs.getString("Username"));
				userDetails.setPhone(rs.getString("Phone"));
				userDetails.setTimeZone(rs.getString("TimeZone"));
				userDetails.setTitle(rs.getString("Title"));
				userDetailsList.add(userDetails);
			}
			
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(), ex.fillInStackTrace());
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		return userDetailsList;
	}
	

}
