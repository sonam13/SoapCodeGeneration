package com.tcs.soapCodeGeneration.Service;

import com.tcs.soapCodeGeneration.Request.Request;

public interface SoapCodeGenerationService {

	public void generateController(Request request);
	public void generateService(Request request);
	public void generateServiceImpl(Request request);
	public void generateSoapService(Request request);
	public void generateSoapHelper(Request request);
	public void generatePojo(String xmlString,Request request);
	public void XmlToPojo(Request request) ;	
}
