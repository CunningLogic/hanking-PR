package com.hanking.pr.utils;


import java.util.HashMap;
import java.util.Map;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class WebServiceUtil {
	
	private static final String CER_ADDRESS ="/home/hanking/pr/jboss6/server/standard/djicorp.jks";
	
	public static String callWebservice(String url,String option,String nameSpace,String param,String paramName) throws AxisFault{
		if(null!=param&&null!=paramName){
			Map<String,String>map=new HashMap<String, String>();
			map.put(paramName, param);
			return callWebservice(url, option, nameSpace, map);
		}
		return callWebservice(url, option, nameSpace, null);
    }  
	
	public static String callWebserviceHttps(String url,String option,String nameSpace,String param,String paramName) throws AxisFault{
		if(null!=param&&null!=paramName){
			Map<String,String>map=new HashMap<String, String>();
			map.put(paramName, param);
			return callWebserviceHttps(url, option, nameSpace, map,CER_ADDRESS);
		}
		return callWebserviceHttps(url, option, nameSpace, new HashMap<String,String>(),CER_ADDRESS);
    }
	/**
	 * Map<String,String>params  key:参数名，value：值
	 * 
	 * */
	
	public static String callWebservice(String url,String option,String nameSpace,Map<String,String>params) throws AxisFault{
    	ServiceClient sc = new ServiceClient();
 		Options opts = new Options(); 

		EndpointReference end = new EndpointReference(url);
		opts.setTo(end);
		opts.setAction((nameSpace.endsWith("/")?nameSpace:(nameSpace+"/"))+option);
		sc.setOptions(opts);
		
		OMFactory fac = OMAbstractFactory.getOMFactory();  
		OMNamespace omNs = fac.createOMNamespace(nameSpace, "");
		OMElement method = fac.createOMElement(option,omNs);
		if(params != null && params.size()> 0){
			for (Map.Entry<String,String> entry : params.entrySet()) {  				
				OMElement value = fac.createOMElement(entry.getKey(),omNs);
				value.setText(entry.getValue());
				method.addChild(value); 
				
//			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			} 
		} 

		OMElement res = sc.sendReceive(method);
		return res.getFirstElement().getText();
    }  
	
	public static String callWebserviceHttps(String url,String option,String nameSpace,Map<String,String>params,String cerAddress) throws AxisFault{
		System.setProperty("javax.net.ssl.trustStore", cerAddress);
		ServiceClient sc = new ServiceClient();
    	
 		Options opts = new Options(); 

		EndpointReference end = new EndpointReference(url);
		opts.setTo(end);
		opts.setAction((nameSpace.endsWith("/")?nameSpace:(nameSpace+"/"))+option);
		sc.setOptions(opts);
		
		OMFactory fac = OMAbstractFactory.getOMFactory();  
		OMNamespace omNs = fac.createOMNamespace(nameSpace, "");
		OMElement method = fac.createOMElement(option,omNs);
		if(params != null && params.size()> 0){
			for (Map.Entry<String,String> entry : params.entrySet()) {  				
				OMElement value = fac.createOMElement(entry.getKey(),omNs);
				value.setText(entry.getValue());
				method.addChild(value); 
				
//			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			} 
		} 

		OMElement res = sc.sendReceive(method);
		return res.getFirstElement().getText();
    }  
	
	/**
	 * 用RCP方式调用Axis服务端
	 * 
	 * @param wsdl
	 * @param method
	 * @param args
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> rcpInvokeWebService(String wsdl, String method, Object args[],String cerAddress) {
//		// 使用RPC方式调用WebService
//		
//		RPCServiceClient serviceClient;
//		System.setProperty("javax.net.ssl.trustStore", cerAddress);
//		 
//		ObjectMapper objectMapper = new ObjectMapper();
//		Map<String, Object> map = new HashMap<String, Object>();
//		try {
//			serviceClient = new RPCServiceClient();
//			// 指定调用WebService的URL
//			EndpointReference targetEPR = new EndpointReference(wsdl);
//			Options options = serviceClient.getOptions();
//			// 确定目标服务地址
//			options.setTo(targetEPR);
//			// 设置命名空间以及调用方法名字
//			QName qname = new QName("", method);
//			// 调用方法一 传递参数，调用服务，获取服务返回结果集
//			OMElement element = serviceClient.invokeBlocking(qname, args);
//			// 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
//			// 我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
//			String result = element.getFirstElement().getText();
//			map = objectMapper.readValue(result, Map.class);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return map;
//	}
	
}
