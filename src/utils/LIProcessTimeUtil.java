package utils;

import org.joda.time.DateTime;
import org.joda.time.Period;

import constant.LINumConstant;
import constant.LIStrNumConstant;
import constant.LIStrProcConstant;
import logger.LoggerUtil;


public class LIProcessTimeUtil {
	
	public static void end(final DateTime startTime, final DateTime endTime, final boolean bFromMethod, final String strMethodName) {

		// find the hour, minute, second and millisecond component
		Period p = new Period(startTime, endTime);
		
		int hours = p.getHours();
		int minutes = p.getMinutes();
		int seconds = p.getSeconds();
		int milliSeconds = p.getMillis();

		StringBuilder sbHour = null;
		StringBuilder sbMin = null;
		StringBuilder sbSeconds = null;
		StringBuilder sbMilli = null;

		if (hours < LINumConstant.INT_TEN) {
			sbHour = new StringBuilder(LIStrNumConstant.STR_ZERO).append(String.valueOf(hours));
		} else {
			sbHour = new StringBuilder(String.valueOf(hours));
		}

		if (minutes < LINumConstant.INT_TEN) {
			sbMin = new StringBuilder(LIStrNumConstant.STR_ZERO).append(String.valueOf(minutes));
		} else {
			sbMin = new StringBuilder(String.valueOf(minutes));
		}
		
		if(seconds < LINumConstant.INT_TEN) {
			sbSeconds = new StringBuilder(LIStrNumConstant.STR_ZERO).append(String.valueOf(seconds));
		} else {
			sbSeconds = new StringBuilder(String.valueOf(seconds));
		}
		if (milliSeconds < LINumConstant.INT_TEN) {
			sbMilli = new StringBuilder(LIStrNumConstant.STR_ZERO).append(LIStrNumConstant.STR_ZERO).append(String.valueOf(milliSeconds));
		} else if (milliSeconds < LINumConstant.INT_HUNDRED) {
			sbMilli = new StringBuilder(LIStrNumConstant.STR_ZERO).append(String.valueOf(milliSeconds));
		} else {
			sbMilli = new StringBuilder(String.valueOf(milliSeconds));
		}
		
		if( !bFromMethod ) {
			LoggerUtil.info(String.format(LIStrProcConstant.PROC_ELAPSED_TIME, sbHour.toString(), sbMin.toString(), sbSeconds.toString(),
					sbMilli.toString()));
		} else {
		    LoggerUtil.info(String.format(LIStrProcConstant.METHOD_ELAPSED_TIME, strMethodName, sbHour.toString(), sbMin.toString(), 
					sbSeconds.toString(), sbMilli.toString()));
		}
	}
}
