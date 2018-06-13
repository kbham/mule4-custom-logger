package com.mulesoft.services.internal.metadata;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import com.mulesoft.services.internal.metadata.LoggerUtils.LogCategory;


public class ExceptionDetails {
	

	@Parameter
	@Example("#[error.exception.message]")
	@Optional(defaultValue="")
	@DisplayName("Exception Message")
	String exceptionMsg;
	
	   
   public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	@Parameter
	@Optional(defaultValue="")
	@Example("#[error.exception.detailMessage]")
	@DisplayName("Exception Detail Message")
	String exceptionDetailMsg;


	public String getExceptionDetailMsg() {
		return exceptionDetailMsg;
	}

	public void setExceptionDetailMsg(String exceptionDetailMsg) {
		this.exceptionDetailMsg = exceptionDetailMsg;
	}
	
	
	@Parameter
	@Optional(defaultValue="BUSINESS")
	@DisplayName("Log Exception Classification")
	ExceptionType exceptionType;


	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	
	@Parameter
	   @Optional(defaultValue="MEDIUM")
	   @DisplayName("Exception Severity")
	   ExceptionSeverity exceptionSeverity;




	
	public ExceptionSeverity getExceptionSeverity() {
		return exceptionSeverity;
	}

	public void setExceptionSeverity(ExceptionSeverity exceptionSeverity) {
		this.exceptionSeverity = exceptionSeverity;
	}
	
	   @Parameter
	   @Optional(defaultValue="BASE")
	   @DisplayName("Log Category")
	   @Summary(LoggerUtils.LOG_CATEGORY_DESC)
	   LogCategory exceptionCategory;
	   
	   
	public LogCategory getLogCategory() {
		return exceptionCategory;
	}

	public void setLogCategory(LogCategory exceptionCategory) {
		this.exceptionCategory = exceptionCategory;
	}

	/**
	 * Business Exceptions as defined by 
	 *
	 */
	public enum ExceptionType  {
		BUSINESS("01"),
	    APPLICATION("02"),
	    SYSTEM("04");
		
		private String type;

		ExceptionType(String type) {
	        this.type = type;
	    }

	    public String type() {
	        return type;
	    }
	}
	
	public enum ExceptionSeverity {
		CRITICAL("01"),
		HIGH("02"),
		MEDIUM("03"),
	    LOW("04"),
	    LOWEST("05");
		
		private String severity;

		ExceptionSeverity(String severity) {
	        this.severity = severity;
	    }

	    public String severity() {
	        return severity;
	    }
	}

	
}
