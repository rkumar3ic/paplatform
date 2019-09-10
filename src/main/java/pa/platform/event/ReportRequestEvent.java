package pa.platform.event;

public class ReportRequestEvent extends PaNotificationEvent{
	
	public String action;
	private Integer currentReportStage;
	private String requestedOnBehalf;

	
	public ReportRequestEvent(String action,Integer brandId, 
			Integer reportId, Integer currentReportStage, Integer reportType,
			Integer userId, String userName, String requestedOnBehalf) {
		super(brandId,reportId,reportType,userId,userName);
		this.action = action;
		this.currentReportStage = currentReportStage;
		this.requestedOnBehalf = requestedOnBehalf;
	
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getCurrentReportStage() {
		return currentReportStage;
	}

	public void setCurrentReportStage(Integer currentReportStage) {
		this.currentReportStage = currentReportStage;
	}

	public String getRequestedOnBehalf() {
		return requestedOnBehalf;
	}

	public void setRequestedOnBehalf(String requestedOnBehalf) {
		this.requestedOnBehalf = requestedOnBehalf;
	}



	
	

}
