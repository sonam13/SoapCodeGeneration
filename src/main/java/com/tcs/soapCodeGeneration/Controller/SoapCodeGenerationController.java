/**
 * 
 */
package com.tcs.soapCodeGeneration.Controller;
import java.io.File;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.soapCodeGeneration.Request.Request;
import com.tcs.soapCodeGeneration.Service.SoapCodeGenerationService;

@Controller
public class SoapCodeGenerationController 
{
	
	@Autowired 
	SoapCodeGenerationService service;
	@RequestMapping(value = "/soapCodeGeneration", method = RequestMethod.POST)
	public @ResponseBody String getClientes(@RequestBody Request request) {
		String msg=null;
		try
		{
		
		String filePath=request.getServicePath();
		 File f = new File(filePath);
		 try {
			FileUtils.deleteDirectory(f);
			new File(filePath).mkdirs();
			new File(filePath+ File.separator+"controller").mkdirs();
			new File(filePath+ File.separator+"pojoRequest").mkdirs();
			new File(filePath+ File.separator+"service").mkdirs();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		service.generateController(request);
		service.generateService(request);
		service.generateServiceImpl(request);
		service.generateSoapHelper(request);
		service.generateSoapService(request);
		service.generatePojo(request.getServiceInputParameters(), request);
		msg= "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			msg="Exception generated while code generation";
		}
		return msg;
		
	}
	 
	}
	
