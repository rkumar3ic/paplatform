package fb.auditTrail.dao.impl;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fb.auditTrail.dao.AuditDAO;
import fb.auditTrail.model.AuditTrail;

public class AuditDAOImpl implements AuditDAO {
	
	private static Logger logger = Logger.getLogger(AuditDAOImpl.class);

	@Override
	public void insertAuditDetails(int brandId, AuditTrail auditTrail) {
		logger.debug("insertAuditDetails...");
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase database = mongoClient.getDatabase("AuditTrail");
		database.createCollection("auditTrail");
		MongoCollection<Document> collection = database.getCollection("auditTrail");
		 Document document = new Document("brandId", brandId) 
	      .append("auditTrail", auditTrail);  
	      collection.insertOne(document); 
	      System.out.println("Document inserted successfully");   

	}

}
