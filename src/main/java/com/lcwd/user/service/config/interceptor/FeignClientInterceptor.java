//package com.lcwd.user.service.config.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//
//@Configuration
//@Component
//public class FeignClientInterceptor implements RequestInterceptor {
//
//	private OAuth2AuthorizedClientManager manager;
//	
//	@Override
//	public void apply(RequestTemplate template) {
//		
//		template.header("Authorization", "Bearer "+token)
//	}
//
//	
//}
