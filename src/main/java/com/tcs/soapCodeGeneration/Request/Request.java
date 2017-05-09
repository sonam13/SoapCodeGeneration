package com.tcs.soapCodeGeneration.Request;

import java.util.ArrayList;

public class Request {

	
	private String serviceType;
	private String serviceMethod;
	private String serviceQueryParameters;
	private String serviceUrl;
	private String servicePath;
	private String serviceOperationName;
	private String serviceTargetNameSpace;
	private String serviceInputParameters;
	private ArrayList<String> customResponse;
	private String serviceProduces;
	private String serviceConsumes;
	private Object serviceResponse;
	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return the serviceMethod
	 */
	public String getServiceMethod() {
		return serviceMethod;
	}
	/**
	 * @param serviceMethod the serviceMethod to set
	 */
	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}
	/**
	 * @return the serviceQueryParameters
	 */
	public String getServiceQueryParameters() {
		return serviceQueryParameters;
	}
	/**
	 * @param serviceQueryParameters the serviceQueryParameters to set
	 */
	public void setServiceQueryParameters(String serviceQueryParameters) {
		this.serviceQueryParameters = serviceQueryParameters;
	}
	/**
	 * @return the serviceUrl
	 */
	public String getServiceUrl() {
		return serviceUrl;
	}
	/**
	 * @param serviceUrl the serviceUrl to set
	 */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	/**
	 * @return the servicePath
	 */
	public String getServicePath() {
		return servicePath;
	}
	/**
	 * @param servicePath the servicePath to set
	 */
	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}
	/**
	 * @return the serviceOperationName
	 */
	public String getServiceOperationName() {
		return serviceOperationName;
	}
	/**
	 * @param serviceOperationName the serviceOperationName to set
	 */
	public void setServiceOperationName(String serviceOperationName) {
		this.serviceOperationName = serviceOperationName;
	}
	/**
	 * @return the serviceTargetNameSpace
	 */
	public String getServiceTargetNameSpace() {
		return serviceTargetNameSpace;
	}
	/**
	 * @param serviceTargetNameSpace the serviceTargetNameSpace to set
	 */
	public void setServiceTargetNameSpace(String serviceTargetNameSpace) {
		this.serviceTargetNameSpace = serviceTargetNameSpace;
	}
	/**
	 * @return the serviceInputParameters
	 */
	public String getServiceInputParameters() {
		return serviceInputParameters;
	}
	/**
	 * @param serviceInputParameters the serviceInputParameters to set
	 */
	public void setServiceInputParameters(String serviceInputParameters) {
		this.serviceInputParameters = serviceInputParameters;
	}
	/**
	 * @return the customResponse
	 */
	public ArrayList<String> getCustomResponse() {
		return customResponse;
	}
	/**
	 * @param customResponse the customResponse to set
	 */
	public void setCustomResponse(ArrayList<String> customResponse) {
		this.customResponse = customResponse;
	}
	/**
	 * @return the serviceProduces
	 */
	public String getServiceProduces() {
		return serviceProduces;
	}
	/**
	 * @param serviceProduces the serviceProduces to set
	 */
	public void setServiceProduces(String serviceProduces) {
		this.serviceProduces = serviceProduces;
	}
	/**
	 * @return the serviceConsumes
	 */
	public String getServiceConsumes() {
		return serviceConsumes;
	}
	/**
	 * @param serviceConsumes the serviceConsumes to set
	 */
	public void setServiceConsumes(String serviceConsumes) {
		this.serviceConsumes = serviceConsumes;
	}
	/**
	 * @return the serviceResponse
	 */
	public Object getServiceResponse() {
		return serviceResponse;
	}
	/**
	 * @param serviceResponse the serviceResponse to set
	 */
	public void setServiceResponse(Object serviceResponse) {
		this.serviceResponse = serviceResponse;
	}

	
}