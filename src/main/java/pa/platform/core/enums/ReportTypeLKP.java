package pa.platform.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum ReportTypeLKP {
	
	Pricing_Analysis(1), 
	Menu_Optimisations(2), 
	Consumer_Feedback(3), 
	Price_Watch(4), 
	Price_Position(5), 
	Competitor_Price_Insight(6); 
	
	
	private final int reportType;
	
	ReportTypeLKP(int reportType){
		this.reportType = reportType;
	}
	
	private static final Map<Integer, ReportTypeLKP> reportTypesMap = new HashMap<Integer, ReportTypeLKP>();
	static {
		for(ReportTypeLKP rtlkup: ReportTypeLKP.values()){
			reportTypesMap.put(rtlkup.getReportType(), rtlkup);
		}
	}

	public int getReportType() {
		return reportType;
	}
	
	public static String getReportType(int reportType){
		return reportTypesMap.get(reportType).name();
	}
}
