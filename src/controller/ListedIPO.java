package controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;

import constant.NumConst;
import constant.StrLtrConst;
import constant.StrProcConst;
import logger.LoggerUtil;
import logger.LoggerWritingUtil;
import service.ListedIPOService;
import utils.LIProcessTimeUtil;

public class ListedIPO {
	
	public static void main(String[] args) {
		
		String strClassName = ListedIPO.class.getSimpleName();
        String strMethodName = Thread.currentThread().getStackTrace()[NumConst.INT_ONE]
                .getMethodName();
		
		ListedIPO mainObj = new ListedIPO();
		
        Runtime rt = null;
        DateTime startTime = null;
        DateTime endTime = null;
        int exitStatus = NumConst.INT_ZERO;
        
        
		try{
			LoggerUtil.info(String.format(StrProcConst.AC_PROC_SE, StrLtrConst.AC_START));
            LoggerWritingUtil.writeLoggerInfo(true, strMethodName, strClassName);

            startTime = new DateTime();

            rt = Runtime.getRuntime();
            
            mainObj.execute();
            
            endTime = new DateTime();
            
            LoggerWritingUtil.writeLoggerInfo(false, strMethodName, strClassName);
            LIProcessTimeUtil.end(startTime, endTime, false, StrLtrConst.STR_EMPTY);
            LoggerUtil.info(String.format(StrProcConst.AC_PROC_SE, StrLtrConst.AC_END));
		}catch(Exception ex) {
            exitStatus = NumConst.INT_ONE;
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
