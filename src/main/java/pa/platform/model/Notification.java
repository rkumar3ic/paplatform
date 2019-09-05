package pa.platform.model;

import java.util.Map;

public class Notification {
	
	private int reportId;
	private int reportStage;
	private int brandId;
	private String userName;
	private String notifcationText;
	private boolean isReviewed;
	private String reviewDate;
	private String reviewedBy;
	private String createdBy;
	private String createdDate;
	private int eventId;
	private int userId;
	
	private String role;
	private int reportType;

	private String fromAddress;
	private String emailAddress;
	
	private int currentReportStage;
	private int newReportStage;
	
	private Map<String,String> mergeCodesMap;
	
	public Notification(){
		
	}
	
	
	public Notification(int reportId, int brandId, String userName,String notifcationText, String createdDate, String role) {
		super();
		this.reportId = reportId;
		this.brandId = brandId;
		this.userName = userName;
		this.notifcationText = notifcationText;
		this.createdDate = createdDate;
		this.role = role;
	}
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}  
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNotifcationText() {
		return notifcationText;
	}
	public void setNotifcationText(String notifcationText) {
		this.notifcationText = notifcationText;
	}
	public boolean isReviewed() {
		return isReviewed;
	}
	public void setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewedBy() {
		return reviewedBy;
	}
	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getReportStage() {
		return reportStage;
	}
	public void setReportStage(int reportStage) {
		this.reportStage = reportStage;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getReportType() {
		return reportType;
	}
	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Map<String, String> getMergeCodesMap() {
		return mergeCodesMap;
	}


	public void setMergeCodesMap(Map<String, String> mergeCodesMap) {
		this.mergeCodesMap = mergeCodesMap;
	}


	public int getCurrentReportStage() {
		return currentReportStage;
	}


	public void setCurrentReportStage(int currentReportStage) {
		this.currentReportStage = currentReportStage;
	}


	public int getNewReportStage() {
		return newReportStage;
	}


	public void setNewReportStage(int newReportStage) {
		this.newReportStage = newReportStage;
	}
	
}
