package com.tcs.soapCodeGeneration.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tcs.soapCodeGeneration.Request.Request;
@Service
public class SoapCodeGenerationServiceImpl implements SoapCodeGenerationService {


public void generateController(Request request)
{
	 StringBuilder classFormat = new StringBuilder();
	 System.out.println(request.getServiceMethod());
	 classFormat.append("package;\n\n");
	 classFormat.append("import org.springframework.beans.factory.annotation.Autowired;\n");
	 classFormat.append("import org.springframework.http.MediaType;\n");
	 classFormat.append("import org.springframework.web.bind.annotation.RequestBody;\n");
	 classFormat.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
	 classFormat.append("import org.springframework.web.bind.annotation.RequestMethod;\n");
	 classFormat.append("import org.springframework.web.bind.annotation.ResponseBody;\n");
	/*classFormat.append("import generatedClasses."+request.getServiceOperationName()+"request;\n");
	classFormat.append("import generatedClasses."+request.getServiceOperationName()+"response\n");
	classFormat.append("import generatedClasses."+request.getServiceOperationName()+"service\n\n");*/	
	 classFormat.append("public class "+ request.getServiceOperationName()+"Controller {\n\n");
	 classFormat.append("@Autowired\n");
	 classFormat.append(request.getServiceOperationName()+"Service "+request.getServiceOperationName().toLowerCase()+"Service;\n\n");
	 classFormat.append("@RequestMapping(method = RequestMethod."+request.getServiceMethod().toUpperCase()+", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE, value =\"/"+request.getServiceOperationName()+"\")\n");
	 classFormat.append("public @ResponseBody\n");
	 classFormat.append("String get"+request.getServiceOperationName()+"Details(@RequestBody "+request.getServiceOperationName()+"Request request) {\n\n");
	 classFormat.append("String response = "+request.getServiceOperationName().toLowerCase()+"Service.get"+request.getServiceOperationName()+"Details(request);\n");
	 classFormat.append("return response;\n");
	 classFormat.append("}\n}\n");
	 
	 FileOutputStream fop = null;
		File file;
		file = new File(request.getServicePath()+"\\controller\\"+request.getServiceOperationName()+"Controller.java");
		String content = classFormat.toString();
		try {
			fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
public void generateService(Request request)
{
FileOutputStream fop = null;
File file;
StringBuilder classFormatService = new StringBuilder(); 
classFormatService.append("package;\n\n");
classFormatService.append("public interface "+request.getServiceOperationName()+"Service {\n\n");
classFormatService.append("String get"+request.getServiceOperationName()+"Details("+request.getServiceOperationName()+"Request request);\n");
classFormatService.append("\n}");
	
	file = new File(request.getServicePath()+"\\service\\"+request.getServiceOperationName()+"Service.java");
	String contents = classFormatService.toString();
	try {
		fop = new FileOutputStream(file);
		if (!file.exists()) {
			file.createNewFile();
		}
		byte[] contentInBytes = contents.getBytes();
		fop.write(contentInBytes);
		fop.flush();
		fop.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		try {
			if (fop != null) {
				fop.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
public void generateServiceImpl(Request request)
{
	 FileOutputStream fop = null;
	 File file;
	 StringBuilder classFormat = new StringBuilder(); 
	 classFormat.append("package;\n\n");
	 classFormat.append("import org.springframework.beans.factory.annotation.Autowired;\n");
	 classFormat.append("public class "+ request.getServiceOperationName()+"ServiceImpl {\n\n");
	 classFormat.append("\t@Autowired\n");
	 classFormat.append("\tprivate SOAPService soapService;\n\n");
	 classFormat.append("\tprivate static final String "+request.getServiceOperationName().toUpperCase()+"_SOAP_ACTION= \""+request.getServiceTargetNameSpace()+request.getServiceOperationName()+"\";\n");
	 classFormat.append("\tprivate static final String serviceUrl =\""+request.getServiceUrl()+"\";\n");
	 classFormat.append("\tprivate static final String serviceOperationName =\""+request.getServiceOperationName()+"\";\n");
	 classFormat.append("\tprivate static final String serviceTargetNameSpace =\""+request.getServiceTargetNameSpace()+"\";\n");
	 classFormat.append("\tpublic String get"+request.getServiceOperationName()+"Details("+request.getServiceOperationName()+"Request request) {\n\n");
	 classFormat.append("\tString responseXML = null;\n");
	 classFormat.append("\tString requestXML = getSoapRequestXML(request,serviceOperationName,serviceTargetNameSpace);\n");
	 classFormat.append("\tSystem.out.println(requestXML);\n");
	 classFormat.append("\tresponseXML = soapService.invokeService(requestXML,"+request.getServiceOperationName().toUpperCase()+"_SOAP_ACTION, serviceUrl);\n");
	 classFormat.append("\treturn responseXML;\n");
	 classFormat.append("}\n\n\n");
	 
	 classFormat.append("public String getSoapRequestXML(String requestBody,String OperationName,String TargetNameSpace) {\n\n");
	 classFormat.append("\tStringBuilder req = new StringBuilder();\n");
	 classFormat.append("\treq.append(\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\">\");\n");
	 classFormat.append("\treq.append(\"<soap:Envelope xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\"\");\n");
	 classFormat.append("\treq.append(\" xmlns:xsd=\\\"http://www.w3.org/2001/XMLSchema\\\"\");\n");
	 classFormat.append("\treq.append(\" xmlns:soap=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\">\");\n");
	 classFormat.append("\treq.append(\"<soap:Body>\");\n");
	 classFormat.append("req.append(requestBody.replace(\"<\"+OperationName+\">\", \"<\"+OperationName+\" xmlns=\"\"+TargetNameSpace+\"\\\">\"));");
	 classFormat.append("\treq.append(\"</soap:Body>\");\n");
	 classFormat.append("\treq.append(\"</soap:Envelope>\");\n");
	 classFormat.append("\treturn req.toString();\n");
	 classFormat.append("}\n\n\n");
	 
	 classFormat.append("public static String trimLast(String str) \n{\n\n");
	 classFormat.append("\tif (str != null && str.length() > 0 && str.charAt(str.length()-1)=='/') {\n");
	 classFormat.append("\tstr = str.substring(0, str.length()-1);\n");
	 classFormat.append("\t}\n");
	 classFormat.append(" \treturn str;\n");
	 classFormat.append("\t}\n}\n\n");
	 

	 file = new File(request.getServicePath()+"\\service\\"+request.getServiceOperationName()+"ServiceImpl.java");
		String contents = classFormat.toString();
		try {
			fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = contents.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}

	 
	 
		public void generateSoapService(Request request)
		 {
			FileOutputStream fop = null;
			File file;
			StringBuilder classFormat = new StringBuilder(); 
			classFormat.append("package;\n\n");
			classFormat.append("import javax.xml.bind.JAXBException;\n");
			classFormat.append("\tpublic interface SOAPHelperImpl \n\t{\n");
			classFormat.append("\t\tString invokeService(String soapRequest, String soapAction, String endPoint);\n\n");
			classFormat.append("\t\tString objectToXml("+request.getServiceOperationName()+"Request request) throws JAXBException;\n");
			classFormat.append("\t}");
			 
			
			 file = new File(request.getServicePath()+"\\service\\SOAPHelperImpl.java");
				String contents = classFormat.toString();
				try {
					fop = new FileOutputStream(file);
					if (!file.exists()) {
						file.createNewFile();
					}
					byte[] contentInBytes = contents.getBytes();
					fop.write(contentInBytes);
					fop.flush();
					fop.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (fop != null) {
							fop.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		 
}	
		public void generateSoapHelper(Request request)
		 {
			FileOutputStream fop = null;
			File file;
			StringBuilder classFormat = new StringBuilder(); 
			classFormat.append("package;\n\n");
			classFormat.append("import java.io.ByteArrayInputStream;\n");
			classFormat.append("import java.io.ByteArrayOutputStream;\n");
			classFormat.append("import java.io.IOException;\n");
			 classFormat.append("import javax.xml.bind.JAXBContext;\n");
			 classFormat.append("import javax.xml.bind.JAXBException;\n");
			 classFormat.append("import javax.xml.bind.Marshaller;\n");
			 classFormat.append("import javax.xml.messaging.URLEndpoint;\n");
			 classFormat.append("import javax.xml.soap.MessageFactory;\n");
			 classFormat.append("import javax.xml.soap.MimeHeaders;\n");
			 classFormat.append("import javax.xml.soap.SOAPConnection;\n");
			 classFormat.append("import javax.xml.soap.SOAPConnectionFactory;\n");
			 classFormat.append("import javax.xml.soap.SOAPException;\n");
			 classFormat.append("import javax.xml.soap.SOAPMessage;\n");
			 classFormat.append("public class SoapHelper implements SOAPHelperImpl {\n\n");
			 classFormat.append("\tpublic String objectToXml("+request.getServiceOperationName()+"Request request) throws JAXBException{\n\n");
			 classFormat.append("\t\tByteArrayOutputStream os=new ByteArrayOutputStream();\n");
			 classFormat.append("\t\tJAXBContext context = JAXBContext.newInstance("+request.getServiceOperationName()+"Request.class);\n");
			 classFormat.append("\t\tMarshaller m = context.createMarshaller();\n");
			 classFormat.append("\t\tm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);\n");
			 classFormat.append("\t\tm.marshal(request, os);\n");
			 classFormat.append("\t\treturn new String(os.toByteArray()).replace(\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\" standalone=\\\"yes\\\"?>\",\"\");\n");
			 classFormat.append("\t}\n\n\n");
			 classFormat.append("\tpublic String invokeService(String soapRequest, String soapAction, String endPoint) \n{\n");
			 classFormat.append("\t\tSOAPConnectionFactory myFct = null;\n");
			 classFormat.append("\t\tSOAPConnection myCon = null;\n");
			 classFormat.append("\t\tSOAPMessage message = null;\n");
			 classFormat.append("\t\ttry {\n");
			 classFormat.append("\t\tmyFct = SOAPConnectionFactory.newInstance();\n");
			 classFormat.append("\t\tmyCon = myFct.createConnection();\n");
			 classFormat.append("\t\tMessageFactory myMsgFct = MessageFactory.newInstance();\n");
			 classFormat.append("\t\tMimeHeaders headers = new MimeHeaders();\n");
			 classFormat.append("\t\tByteArrayInputStream is = new ByteArrayInputStream(soapRequest.getBytes());\n");
			 classFormat.append("\t\tmessage = myMsgFct.createMessage(headers, is);\n");
			 classFormat.append("\t\tMimeHeaders headers1 = message.getMimeHeaders();\n");
			 classFormat.append("\t\theaders1.addHeader(\"SOAPAction\", soapAction);\n");
			 classFormat.append("\t\theaders1.addHeader(\"Accept\", \"application/soap+xml, text/xml\");\n");
			 classFormat.append("\t\tByteArrayOutputStream requestOS = new ByteArrayOutputStream();\n");
			// classFormat.append("");
			 classFormat.append("\t\tmessage.saveChanges();\n");
			 classFormat.append("\t\tmessage.writeTo(requestOS);\n");
			 classFormat.append("\t\t}\n\t\tcatch (UnsupportedOperationException e) {\n");
			 classFormat.append("\t\t\te.printStackTrace();\n");
			 classFormat.append("\t\t} \n\t\tcatch (SOAPException e)\n \t\t{\n");
			 classFormat.append("\t\t\te.printStackTrace();\n");
			 classFormat.append("\t\t} \n\t\tcatch (IOException e) \n\t\t{\n");
			 classFormat.append("\t\t\te.printStackTrace();\n\t\t}\n");
			 classFormat.append("\t\tURLEndpoint endPt = new URLEndpoint(endPoint);\n");
			 classFormat.append("\t\tSOAPMessage reply = null;\n");
			 classFormat.append("\t\tByteArrayOutputStream responseOS = new ByteArrayOutputStream();\n");
			 classFormat.append("\t\ttry {\n");
			 classFormat.append("\t\t\treply = myCon.call(message, endPt);\n");
			 classFormat.append("\t\t\treply.writeTo(responseOS);\n");
			 classFormat.append("\t\t}\n\t\tcatch (SOAPException e) {\n");
			 classFormat.append("\t\t\te.printStackTrace();\n");
			 classFormat.append("\t\t} \n\t\tcatch (IOException e) {\n");
			 classFormat.append("\t\t\te.printStackTrace();\n");
			 classFormat.append("\t\t}\n\t\t finally {\n");
			 classFormat.append("\t\ttry {\n");
			 classFormat.append("\t\t\tmyCon.close();\n");
			 classFormat.append("\t\t}\n\t\t catch (SOAPException e) {}\n\t\t}\n");
			 classFormat.append("\t\tString soapResponse = new String(responseOS.toByteArray());\n");
			 classFormat.append("\t\t\treturn soapResponse;\n\t}\n}\n");
			 
			 
			 file = new File(request.getServicePath()+"\\service\\SoapHelper.java");
				String content = classFormat.toString();
				try {
					fop = new FileOutputStream(file);
					if (!file.exists()) {
						file.createNewFile();
					}
					byte[] contentInBytes = content.getBytes();
					fop.write(contentInBytes);
					fop.flush();
					fop.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (fop != null) {
							fop.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		 }


public void generatePojo(String xmlString,Request request) {
		System.out.println("input:" + xmlString);
		boolean flag = false;
		BufferedWriter bw = null;
		FileWriter fw = null;
		 String xml1=xmlString.replaceAll("><", ">\n<");
		 
		 System.out.println("h66i");
			System.out.println("input:" + xml1);
		
		File stockFile = new File("sample.xml");

		try {
			flag = stockFile.createNewFile();
			FileWriter writer = new FileWriter(stockFile);
			writer.write(xml1);
			writer.flush();
			writer.close();

		} catch (IOException ioe) {
			System.out.println("Error while Creating File in Java" + ioe);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
		System.out.println("file -" + stockFile.getPath() + " created ");
		XmlToPojo(request);
	}

	/**
	 * read from file
	 */
	public void XmlToPojo(Request request) {
		File xmlInFile = new File("sample.xml");
		Document doc = null;
		String rootElement = request.getServiceOperationName()+"Request";
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = dBuilder.parse(xmlInFile);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (doc.hasChildNodes()) {

			getNodeDetails(doc.getChildNodes().item(0).getChildNodes(),
					rootElement,request);
		}

	}

	/**
	 * generate xml to pojo
	 * @param childNodes
	 * @param rootElement
	 */
	private static void getNodeDetails(NodeList childNodes, String rootElement,Request request) {
		StringBuilder classFormat = new StringBuilder();
		StringBuilder getterAndSetter = new StringBuilder();
		classFormat.append("package ;\n");
		classFormat.append("\n\nimport javax.xml.bind.annotation.XmlRootElement;\n");
		classFormat.append("import javax.xml.bind.annotation.XmlAttribute;\n");
		classFormat.append("import javax.xml.bind.annotation.XmlElement;\n");
		classFormat.append("import java.util.List;\n");
		classFormat
				.append("import com.fasterxml.jackson.annotation.JsonProperty;\n\n");
		classFormat.append("@XmlRootElement(name=\"" + rootElement + "\")\n");
		classFormat.append("public class " + rootElement + " {\n");
		boolean isListPresent = false;
		if (childNodes.getLength() / 2 > 1) {
			//node does not has child node
			if ((childNodes.item(1).getNodeName().equals(childNodes.item(3)
					.getNodeName()))) {
				isListPresent = true;
			}

		}
		//node has child node
		for (int i = 1; i < childNodes.getLength() && !isListPresent; i += 2) {

			Node node = childNodes.item(i);
			int noOfChild = node.getChildNodes().getLength() / 2; // total no of
																	// child
																	// nodes

			if ((noOfChild > 0 || node.getAttributes().getLength() > 0)
					&& isListPresent == false) {

				classFormat.append("	private " + node.getNodeName() + " "
						+ node.getNodeName().toLowerCase() + ";\n");
				getterAndSetter.append("\n");
				getterAndSetter.append("	@XmlElement(name=\""
						+ node.getNodeName() + "\")\n");
				getterAndSetter.append("	@JsonProperty(\"" + node.getNodeName()
						+ "\")\n");
				getterAndSetter.append("	public " + node.getNodeName() + " get"
						+ node.getNodeName() + "() {\n");
				getterAndSetter.append("		return "
						+ node.getNodeName().toLowerCase() + ";\n");
				getterAndSetter.append("	}\n\n");

				getterAndSetter.append("	public void set" + node.getNodeName()
						+ "(" + node.getNodeName() + " "
						+ node.getNodeName().toLowerCase() + ") {\n");
				getterAndSetter.append("		this."
						+ node.getNodeName().toLowerCase() + " = "
						+ node.getNodeName().toLowerCase() + ";\n");
				getterAndSetter.append("	}\n\n");

			} else {

				classFormat.append("	private String " + node.getNodeName()
						+ ";\n");
				getterAndSetter.append("\n");
				getterAndSetter.append("	@XmlElement(name=\""
						+ node.getNodeName() + "\")\n");
				getterAndSetter.append("	@JsonProperty(\"" + node.getNodeName()
						+ "\")\n");
				getterAndSetter.append("	public String" + " get"
						+ node.getNodeName() + "() {\n");
				getterAndSetter
						.append("		return " + node.getNodeName() + ";\n");
				getterAndSetter.append("	}\n\n");

				getterAndSetter.append("	public void set" + node.getNodeName()
						+ "(String" + " " + node.getNodeName().toLowerCase()
						+ ") {\n");
				getterAndSetter.append("		" + node.getNodeName() + " = "
						+ node.getNodeName().toLowerCase() + ";\n");
				getterAndSetter.append("	}\n\n");
			}

		}

		if (isListPresent) {
			Node node = childNodes.item(1);
			classFormat.append("	private List<" + node.getNodeName() + "> "
					+ node.getNodeName().toLowerCase() + "List;\n");
			getterAndSetter.append("\n");
			getterAndSetter.append("	@XmlElement(name=\"" + node.getNodeName()
					+ "\")\n");
			getterAndSetter.append("	@JsonProperty(\"" + node.getNodeName()
					+ "\")\n");
			getterAndSetter.append("	public List<" + node.getNodeName()
					+ "> get" + node.getNodeName() + "() {\n");
			getterAndSetter.append("		return "
					+ node.getNodeName().toLowerCase() + "List;\n");
			getterAndSetter.append("	}\n\n");

			getterAndSetter.append("	public void set" + node.getNodeName()
					+ "(List<" + node.getNodeName() + "> "
					+ node.getNodeName().toLowerCase() + ") {\n");
			getterAndSetter.append("		this." + node.getNodeName().toLowerCase()
					+ "List = " + node.getNodeName().toLowerCase() + ";\n");
			getterAndSetter.append("	}\n\n");
		}

		classFormat.append(getterAndSetter.toString());
		classFormat.append("}");

		// save String to file
		FileOutputStream fop = null;
		File file;

		file = new File(request.getServicePath()+"\\pojoRequest\\" + rootElement+ ".java");
		String content = classFormat.toString();
		try {
			fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if ((node.hasChildNodes() && node.getChildNodes().getLength() / 2 >= 1)) {

				getNodeDetails(node.getChildNodes(), node.getNodeName(),request);
				if (isListPresent) {
					break;
				}
			}

			if (node.hasAttributes()) {
				System.out.println(node.getNodeName() + " "
						+ node.hasAttributes());
				getAttrDetails(node.getAttributes(), node.getNodeName(),request);
				if (isListPresent) {
					break;
				}
			}
		}

	}

	private static void getAttrDetails(NamedNodeMap attributes, String nodeName,Request request) {
		System.out.println("in attr");
		StringBuilder classFormat = new StringBuilder();
		StringBuilder getterAndSetter = new StringBuilder();
		classFormat.append("package ;\n");
		classFormat
				.append("\n\nimport javax.xml.bind.annotation.XmlRootElement;\n");
		classFormat.append("import javax.xml.bind.annotation.XmlAttribute;\n");
		classFormat.append("import javax.xml.bind.annotation.XmlElement;\n");
		classFormat.append("import java.util.List;\n");
		classFormat
				.append("import com.fasterxml.jackson.annotation.JsonProperty;\n\n");
		classFormat.append("@XmlRootElement(name=\"" + nodeName + "\")\n");
		classFormat.append("public class " + nodeName + " {\n");
		for (int j = 0; j < attributes.getLength(); j++) {
			Node attr = attributes.item(j);
			classFormat.append("	private String " + attr.getNodeName() + ";\n");
			getterAndSetter.append("\n");
			getterAndSetter.append("	@XmlAttribute(name=\""
					+ attr.getNodeName() + "\")\n");
			getterAndSetter.append("	@JsonProperty(\"" + attr.getNodeName()
					+ "\")\n");
			getterAndSetter.append("	public String" + " get"
					+ attr.getNodeName() + "() {\n");
			getterAndSetter.append("		return " + attr.getNodeName() + ";\n");
			getterAndSetter.append("	}\n\n");

			getterAndSetter.append("	public void set" + attr.getNodeName()
					+ "(String" + " " + attr.getNodeName().toLowerCase()
					+ ") {\n");
			getterAndSetter.append("		" + attr.getNodeName() + " = "
					+ attr.getNodeName().toLowerCase() + ";\n");
			getterAndSetter.append("	}\n\n");

		}
		classFormat.append(getterAndSetter.toString());
		classFormat.append("}");

		FileOutputStream fop = null;
		File file;
		file = new File(request.getServicePath()+"\\pojoRequest\\" + nodeName+ ".java");
		String content = classFormat.toString();
		try {
			fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}



