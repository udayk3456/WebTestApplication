package com.app.web.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

public class InterceptorClass implements HandlerInterceptor {
	
	static final String url="https://springcloudconfigclientapplication.cfapps.io/";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(InterceptorClass.salt()!=null) {
			return true;
		}else {
			response.sendError(403);
			return false;
		}
		
	}
	public static String salt() {
		String password="Uday_Koleti_Test";
		String salt=new String(Base64.encodeBase64(password.getBytes()));
		RestTemplate rt=new RestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/text");
		HttpEntity<String> entity=new HttpEntity<>(salt,headers);
		String random=rt.postForObject(InterceptorClass.url+"/salt",entity,String.class);
		return random;
	}

}
