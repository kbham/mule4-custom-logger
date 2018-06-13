package com.mulesoft.services.internal.metadata;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import com.mulesoft.services.internal.metadata.LoggerUtils.LogCategory;


public class DefaultLogDetails {
	
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

}
