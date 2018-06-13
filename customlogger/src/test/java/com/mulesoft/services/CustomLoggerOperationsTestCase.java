package com.mulesoft.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.junit.Test;

public class CustomLoggerOperationsTestCase extends MuleArtifactFunctionalTestCase {

  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
   */
  @Override
  protected String getConfigFile() {
    return "test-mule-config.xml";
  }

  
  @Test
  public void executeLogCustomException() throws Exception {
    /*String payloadValue = ((String) flowRunner("logCustomExceptionFlow").run()
                                      .getMessage()
                                      .getPayload()
                                       .getValue());*/
	  
	  String payloadValue = ((String) flowRunner("logCustomExceptionFlow").run()
              .getMessage()
              .getPayload()
               .getValue());
   
  }
  
  @Test
  public void executeLogDefault() throws Exception {
    /*String payloadValue = ((String) flowRunner("logCustomExceptionFlow").run()
                                      .getMessage()
                                      .getPayload()
                                       .getValue());*/
	  
	  String payloadValue = ((String) flowRunner("logDefaultFlow").run()
              .getMessage()
              .getPayload()
               .getValue());
   
  }

}
