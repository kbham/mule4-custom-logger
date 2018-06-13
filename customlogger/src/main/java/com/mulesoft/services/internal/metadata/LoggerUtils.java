package com.mulesoft.services.internal.metadata;



public class LoggerUtils {
	
	public static final String LOG_CATEGORY_DESC = "Define the log category otherwise this defaults to 'com.mulesoft.log'. "
			+ "Setting the log category is useful when some log messages need to be sent to a seperate appender.";


	/**
	 * Log levels 
	 *
	 */
	public enum LogLevel  {

		ERROR("01"),
		INFO("02"),
		DEBUG("03"),
		WARN("04"),
		TRACE("05");
		
		private String logLevel;

		LogLevel(String logLevel) {
	        this.logLevel = logLevel;
	    }

	    public String logLevel() {
	        return logLevel;
	    }
	}
	
	public enum LogCategory {
		//Logger package names, expand as necessary
			BASE(Constants.BASE),
		    SECURITY(Constants.SECURITY),
		    AUDIT(Constants.AUDIT),
		    EXCEPTION(Constants.EXCEPTION);
			
			private String category;

			LogCategory(String category) {
		        this.category = category;
		    }

		    public String category() {
		        return category;
		    }
		    
		    public static class Constants {
		        public static final String BASE = "com.client.log";
		        public static final String SECURITY = "com.client.log.security";
		        public static final String AUDIT = "com.client.log.audit";
		        public static final String EXCEPTION = "com.client.log.exception";
		    }
	}	
	
}
