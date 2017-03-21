package com.hanking.pr.utils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebServiceUtilHttps {
	 
	/**
	 * 返回Object
	 * 
	 * @param wsdl
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object webServiceReOb(String wsdl, String method, Object args[],String fileUrl) {
		 
	//	System.setProperty("javax.net.ssl.trustStore", fileUrl);

		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdl);
		Object[] returnResult = null;
		try {
			returnResult = client.invoke(method, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnResult[0];
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> rcpInvokeWebService(String wsdl, String method, Object args[],String fileUrl) {
		// 使用RPC方式调用WebService
		wsdl = "https://workflow.djicorp.com/WebServiceInterface/WFCommonService.asmx";
		RPCServiceClient serviceClient;
		System.setProperty("javax.net.ssl.trustStore", fileUrl);
		 
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			serviceClient = new RPCServiceClient();
			// 指定调用WebService的URL
			EndpointReference targetEPR = new EndpointReference(wsdl);
			Options options = serviceClient.getOptions();
			// 确定目标服务地址
			options.setTo(targetEPR);
			options.setAction("http://tempuri.org/CreateWF");
			// 设置命名空间以及调用方法名字
			QName qname = new QName("http://tempuri.org/", method);
			// 调用方法一 传递参数，调用服务，获取服务返回结果集
			OMElement element = serviceClient.invokeBlocking(qname, args);
			// 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
			// 我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
			String result = element.getFirstElement().getText();
			map = objectMapper.readValue(result, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	
}
