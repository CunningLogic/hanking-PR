package com.hanking.pr.utils;

public class DateTimesUtil {

	/**  
     * 正确地处理整数后自动加零的情况
     * @param sNum  
     * @return  
     */   
    public static String getTimes(double sNum){   
    	double totalSeconds=sNum*86400.0D;  
    	//总的分钟数  
    	int seconds =(int)totalSeconds/60;  
    	//实际小时数  
    	int hours =seconds/60;  
    	int minutes = seconds-hours*60;  
    	//剩余的实际分钟数  
    	StringBuffer sb=new StringBuffer();  
    	if(String.valueOf(hours).length()==1){  
    		sb.append("0"+hours);  
    	}else{  
    		sb.append(hours);  
    	}  
    	sb.append(":");  
    	if(String.valueOf(minutes).length()==1){  
    		sb.append("0"+minutes);  
    	}else{  
    		sb.append(minutes);  
    	}  
    	return sb.toString();  
    }
}
