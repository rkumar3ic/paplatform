package pa.platform.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pa.platform.core.DaoManager;
import pa.platform.dao.TemplateDao;
import pa.platform.model.Template;

public class TemplateDaoImpl implements TemplateDao{

	@Override
	public Template getTemplate(int templateId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Template template = null;
		String query="select * from PricingAnalytics.dbo.Template where Id = ?";
		try{
			connection = DaoManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, templateId);
			rs = preparedStatement.executeQuery();
			while (rs.next()){
				template = new Template();
				template.setId(rs.getInt("Id"));
				template.setBrandId(rs.getInt("BrandId"));
				template.setCreatedBy(rs.getString("CreatedBy"));
				template.setDeleted(rs.getBoolean("Deleted"));
				template.setName(rs.getString("Name"));
				template.setTemplate(rs.getString("Template"));
				template.setType(rs.getString("Type"));
			}
			
		}catch(Exception ex){
		
		}finally {
			DaoManager.close(rs);
			DaoManager.close(preparedStatement);
			DaoManager.close(connection);
		}
		return template;
	}

}
