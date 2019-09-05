package pa.platform.mergecode;

import java.util.Map;

public class MergeCodeUtil {
	
	public static String resolveMergeCodes(String message,Map<String,String> mergeCodesMap) {
		
		 String[] mergeCodesArray = mergeCodesMap.keySet().toArray(new String [mergeCodesMap.size()]);
		  
	    try{
	    	for(int i=0;i<mergeCodesArray.length;i++){
	    		if(mergeCodesArray[i].equalsIgnoreCase("##FISHBOWLTEAM##")){
	    			message=message.replace(mergeCodesArray[i],"Fishbowl Team");
	    		}else{
	    			message=message.replace(mergeCodesArray[i], mergeCodesMap.get(mergeCodesArray[i]) == null ? "" : mergeCodesMap.get(mergeCodesArray[i]));
	    		}
	    	  }
		} catch(Exception ex){
			//logger.error(ExceptionUtils.getStackTrace(ex));
			
		}
		//logger.info("Message after MergeCodes Resolved : "+message);
		return message;
	}

}
