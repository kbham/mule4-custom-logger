package com.mulesoft.services.internal.operation;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import com.mulesoft.services.internal.metadata.DefaultLogDetails;
import com.mulesoft.services.internal.metadata.DefaultLogSettings;
import com.mulesoft.services.internal.metadata.ExceptionDetails;
import com.mulesoft.services.internal.metadata.ExceptionSettings;
import com.mulesoft.services.internal.metadata.LoggerUtils;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 * Author: Ashfaq Framewala
 */
public class CustomLoggerOperations {

	protected transient Log logger;
	
	private static String HOSTNAME;

	static {
		// this is not necessarily perfect, but should be good enough. see
		// http://stackoverflow.com/a/7800008 for details
		if (System.getenv("COMPUTERNAME") != null) {
			// Windows environment variable
			HOSTNAME = System.getenv("COMPUTERNAME");
		} else if (System.getenv("HOSTNAME") != null) {
			// some Linux systems (and maybe UNIX systems) have this
			HOSTNAME = System.getenv("HOSTNAME");
		} else {
			// use the name of "localhost"
			try {
				HOSTNAME = Inet4Address.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				HOSTNAME = "[unknown]";
			}
		}
	}

	/**
	 * This method should be  used in an Error Handler to log additional information about the error
	 * @param settings
	 * @return void
	 */
	/* Author: Ashfaq Framewala
	 * Name of the function is set as logCustomException as logException casues an error in Anypoint Studio
	 */
  @MediaType(value = ANY, strict = false)
  public void logCustomException(@ParameterGroup(name="Settings") ExceptionSettings settings,
		  @ParameterGroup(name="Exception Details") ExceptionDetails details) {
	  
	  initLogger(details.getLogCategory().category());
	  String strExpressionResult = "";
	  
		try {
			if (settings.getStrMessage() != null && settings.getStrMessage() != "") {
				  strExpressionResult = "\nExpression: " + settings.getStrMessage();
			  }
		} catch (IOException e) {
			strExpressionResult = "\nExpression: Error while evaluating expression!";
		} 
	  
	  String strCorrelationId = "";
	  if (settings.getCorrelationId() != null && settings.getCorrelationId() != "") {
		  strCorrelationId = " CorrelationId: " + settings.getCorrelationId();
	  }
	  
	  String strErrorMessage = "";
	  if (details.getExceptionMsg() != null || details.getExceptionMsg() != "") {
		  strErrorMessage = "\n Error message: " + details.getExceptionMsg();
	  }

	  String strErrDetailMessage = "";
	  if (details.getExceptionDetailMsg() != null || details.getExceptionDetailMsg() != "") {
		  strErrDetailMessage = "\nError detail message: " + details.getExceptionDetailMsg();
	  }
	  
	String logMessage = "";
	logMessage = String.format("Host: %s, ExceptionType: %s, Severity: %s, "
			+ "%s \n Message: %s "
			+ "%s %s", 
			HOSTNAME, details.getExceptionType(), details.getExceptionSeverity(), 
			strCorrelationId, settings.getLogMessage(), strExpressionResult, 
			strErrorMessage, strErrDetailMessage);

	logWithLevel(logMessage, settings.getLogLevel().logLevel());
	return;
  }
  
	/**
	 * This method should be  used to log messages to the logger - preferably for the INFO and DEBUG ones
	 * @param settings
	 * @return void
	 */
	/* Author: Ashfaq Framewala
	 * Name of the function is set as logCustomException as logException casues an error in Anypoint Studio
	 */
@MediaType(value = ANY, strict = false)
public void logDefault(@ParameterGroup(name="Settings") DefaultLogSettings settings,
		  @ParameterGroup(name="Details") DefaultLogDetails details) {
	  initLogger(details.getLogCategory().category());
	  String strExpressionResult = "";
	  
		try {
			if (settings.getStrExpression() != null && settings.getStrExpression() != "") {
				  strExpressionResult = "\nExpression: " + settings.getStrExpression();
			  }
		} catch (IOException e) {
			strExpressionResult = "\nExpression: Error while evaluating expression!";
		} 
	  
	  String strCorrelationId = "";
	  if (settings.getCorrelationId() != null && settings.getCorrelationId() != "") {
		  strCorrelationId = " CorrelationId: " + settings.getCorrelationId();
	  }
	  
	  
	String logMessage = "";
	logMessage = String.format("Host: %s,"
			+ "%s \n Message: %s ",
			HOSTNAME,  
			strCorrelationId, settings.getLogMessage(), strExpressionResult);
	

	logWithLevel(logMessage, settings.getLogLevel().logLevel());
	return;
}
  
  
  protected void initLogger(String category) {
		this.logger = LogFactory.getLog(category);
	}
  
  
  protected void logWithLevel(String logMessage, String logLevel) {
	  
	  if (LoggerUtils.LogLevel.ERROR.logLevel().equals(logLevel)) {
		  logger.error(logMessage);
	  }
	  else if (LoggerUtils.LogLevel.WARN.logLevel().equals(logLevel)) {
		  logger.warn(logMessage);
	  }
	  else if (LoggerUtils.LogLevel.DEBUG.logLevel().equals(logLevel)) {
		  logger.debug(logMessage);
	  }
	  else if (LoggerUtils.LogLevel.TRACE.logLevel().equals(logLevel)) {
		  logger.trace(logMessage);
	  }
	  else {
		  logger.info(logMessage);
	  }
	  
  }
  
}
