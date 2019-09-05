package pa.platform.dao;

import java.sql.SQLException;
import java.util.List;

import pa.platform.model.UserDetails;

public interface UserDao {
	
	public UserDetails getUserDetailsByUserId(int userId) throws SQLException,Exception;
	
	public UserDetails getUserDetailsByUserName(String userName) throws SQLException,Exception;

	public List<UserDetails> getAdminUsersByBrandId(int brandId) throws SQLException,Exception;

}
