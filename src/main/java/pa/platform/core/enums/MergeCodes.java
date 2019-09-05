package pa.platform.core.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public enum MergeCodes {
	
	APPLICATIONURL("##APPLICATIONURL##"),
	CLIENTUSERNAME("##CLIENTUSERNAME##"),
	ADMINUSERNAME("##ADMINUSERNAME##"),
	REPORTNAME("##REPORTNAME##"),
	REPORTTYPE("##REPORTTYPE##"),
	OLDSTATUS("##OLDSTATUS##"),
	NEWSTATUS("##NEWSTATUS##");
	
	
	private String description;

	MergeCodes(String description) {
	    this.description = description;
	 }

	public String description() {
	    return description;
	}
	
	public List<String> getAllMergeCodes(){
		List<String> enumNames = Stream.of(MergeCodes.values())
	            				.map(Enum::name)
	            				.collect(Collectors.toList());
		return enumNames;
	}

}
