package pa.platform.targeting.channel;

import pa.platform.dao.impl.NotificationDaoImpl;
import pa.platform.dao.impl.ReportDaoImpl;
import pa.platform.model.Notification;

public class WebClient {
	
	private Notification notif;

	public WebClient(Notification notif) {
		super();
		this.notif = notif;
	}
	
	public void saveWebNotification(){
		NotificationDaoImpl notification = new NotificationDaoImpl();
		notification.saveNotification(notif);
		if(notif.getNewReportStage() > 0){
			new ReportDaoImpl().updateNotification(notif);
		}
		
	}

}
