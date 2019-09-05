package pa.platform.event;

public class ReportRequestEvent extends PaNotificationEvent{
	
	public String action;
	private Integer currentReportStage;

	
	public ReportRequestEvent(String action,Integer brandId, 
			Integer reportId, Integer currentReportStage, Integer reportType,
			Integer userId, String userName) {
		super(brandId,reportId,reportType,userId,userName);
		this.action = action;
		this.currentReportStage = currentReportStage;
	
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



	
	

}
