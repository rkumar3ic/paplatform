package pa.platform.model;

import java.sql.Date;

public class ReportEventNotification {
	

	private int id;
	private int eventId;
	private int brandId;
	private int actionsId;
	private int templateId;
	private int sequence;
	private String data;
	private Date created;
	private String createdBy;
	private boolean deleted;
	private int targetGroup;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getActionsId() {
		return actionsId;
	}
	public void setActionsId(int actionsId) {
		this.actionsId = actionsId;
	}
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getTargetGroup() {
		return targetGroup;
	}
	public void setTargetGroup(int targetGroup) {
		this.targetGroup = targetGroup;
	}
	
	
	


}
