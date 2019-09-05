package pa.platform.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum ReportStageLKP {
	Report_Request_Received(1), Information_Requested(2), Report_Available_for_Review(3), Report_Approved(4), Other(5), Cancelled(6), Request_Revision(7);
	
	private final int reportStage;
	
	ReportStageLKP(int reportStage){
		this.reportStage = reportStage;
	}

	private static final Map<Integer, ReportStageLKP> reportStatusMap = new HashMap<Integer, ReportStageLKP>();
	static {
		for(ReportStageLKP rslkup: ReportStageLKP.values()){
			reportStatusMap.put(rslkup.getReportStage(), rslkup);
		}
	}
	
	public int getReportStage() {
		return reportStage;
	}
	
	public static String getReportStage(int reportStage){
		return reportStatusMap.get(reportStage).name();
	}
	
}
