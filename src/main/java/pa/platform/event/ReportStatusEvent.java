package pa.platform.event;

public class ReportStatusEvent extends PaNotificationEvent{
	
	private String action;

	private Integer currentReportStage;

	private Integer newReportStage;
	
	
	public ReportStatusEvent(String action,Integer brandId,
			Integer reportId, Integer currentReportStage, Integer reportType,
			Integer userId, String userName, Integer newReportStage) {
		super(brandId,reportId,reportType,userId,userName);
		this.action = action;
		this.currentReportStage = currentReportStage;
		this.newReportStage = newReportStage;
	
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

	public Integer getNewReportStage() {
		return newReportStage;
	}
	public void setNewReportStage(Integer newReportStage) {
		this.newReportStage = newReportStage;
	}
	
	
	

}
