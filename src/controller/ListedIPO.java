package controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;

import constant.LINumConstant;
import constant.LIStrLtrConstant;
import constant.LIStrProcConstant;
import logger.LoggerUtil;
import logger.LoggerWritingUtil;
import service.ListedIPOService;
import utils.LIProcessTimeUtil;

public class ListedIPO {
	
	public static void main(String[] args) {
		
		String strClassName = ListedIPO.class.getSimpleName();
        String strMethodName = Thread.currentThread().getStackTrace()[LINumConstant.INT_ONE]
                .getMethodName();
		
		ListedIPO mainObj = new ListedIPO();
		
        Runtime rt = null;
        DateTime startTime = null;
        DateTime endTime = null;
        int exitStatus = LINumConstant.INT_ZERO;
        
        
		try{
			LoggerUtil.info(String.format(LIStrProcConstant.AC_PROC_SE, LIStrLtrConstant.AC_START));
            LoggerWritingUtil.writeLoggerInfo(true, strMethodName, strClassName);

            startTime = new DateTime();

            rt = Runtime.getRuntime();
            
            mainObj.execute();
            
            endTime = new DateTime();
            
            LoggerWritingUtil.writeLoggerInfo(false, strMethodName, strClassName);
            LIProcessTimeUtil.end(startTime, endTime, false, LIStrLtrConstant.STR_EMPTY);
            LoggerUtil.info(String.format(LIStrProcConstant.AC_PROC_SE, LIStrLtrConstant.AC_END));
		}catch(Exception ex) {
            exitStatus = LINumConstant.INT_ONE;
            LoggerUtil.error(ExceptionUtils.getStackTrace(ex));
        }finally {
        	
            rt.exit(exitStatus);
        }
	}
	
	public boolean execute() {
		
		ListedIPOService LIService = new ListedIPOService();
		
		LIService.execute();
		
		return true;
	}
}
