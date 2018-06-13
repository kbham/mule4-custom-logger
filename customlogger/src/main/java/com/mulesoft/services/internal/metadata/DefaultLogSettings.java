package com.mulesoft.services.internal.metadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.parameter.ParameterResolver;


public class DefaultLogSettings {
	@Parameter
	@DisplayName("Log Message")
	@Summary("Message to be logged")
	String  logMessage;

	
	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	@Parameter
	@DisplayName("Log Expression")
	@Summary("Use this parameter if a dw expression has to be logged")
	@Optional(defaultValue="#[]")
	@Example("#[payload]")
	ParameterResolver<InputStream>  expr;

	public ParameterResolver<InputStream> getExpr() {
		return expr;
	}
	
	public String getStrExpression() throws IOException {
		String result = null;
		if (expr == null) return result;
		if (expr.getExpression() == null || expr.getExpression().get().trim().equals("") 
				|| expr.getExpression().get().equals("#[]")) {
			return result;
		}
		
		InputStream stream = null;
		
		try {
			stream = expr.resolve();
		}
		catch (Exception e) {
			return result;
		}
		
		  
		  StringBuilder textBuilder = new StringBuilder();
		  try (Reader reader = new BufferedReader(new InputStreamReader
		      (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
		        int c = 0;
		        try {
					while ((c = reader.read()) != -1) {
					    textBuilder.append((char) c);
					}
		        } catch (Exception e) {
		        		return result;
		        }
		    } catch (Exception e) {
		    		return result;
		    }
		  result = textBuilder.toString();
		  return result;	
	}

	public void setExpr(ParameterResolver<InputStream> expr) {
		this.expr = expr;
	}

	@Parameter
	@DisplayName("Correlation ID")
	@Summary("Correlation ID to be set")
	@Optional(defaultValue="#[message.correlationId]")
	String correlationId;

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
	/*
	@Parameter
	@DisplayName("Log Level")
	@Optional(defaultValue="ERROR")
	LogLevel level;
	
	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public LogLevel getLevel() {
		return level;
	}*/
	
	@Parameter
	@DisplayName("Log Level")
	@Optional(defaultValue="INFO")
	private LoggerUtils.LogLevel logLevel;
	
	
	public LoggerUtils.LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LoggerUtils.LogLevel logLevel) {
		this.logLevel = logLevel;
	}


}
