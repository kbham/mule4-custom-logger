# CustomLogger Extension

Replacement for default Mule logger. This custom logger helps in standardizing the parameters that get logged in the log files.  

## When to use this custom logger?

- When an organization intends to limit or standardize the attributes that get logged
- To enforce logging standards
- Developers often don't log useful information leading to more time spent in troubleshooting
- Standardized logs help in integration with Log analysis tools like Splunk


## How to install this custom logger?


Download the project on your system. 
Install the plugin to a maven repository using command on the root project folder 

**_mvn clean install_** 

## How to add this logger to Mule application? 

Add this dependency to your application pom.xml

```
<groupId>org.mule.connectors</groupId>
<artifactId>mule-customlogger</artifactId>
<version>1.0.0</version>
<classifier>mule-plugin</classifier>
```

##Author: Ashfaq Framewala (ashfaq.framewala@mulesoft.com)
