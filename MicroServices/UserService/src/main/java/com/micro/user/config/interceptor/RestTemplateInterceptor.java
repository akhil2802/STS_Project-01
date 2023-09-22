package com.micro.user.config.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	private OAuth2AuthorizedClientManager manager;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		String tokenValue = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
		
		logger.info("Rest Template interceptor: Token: {}", tokenValue);
		
		request.getHeaders().add("Authorization", "Bearer " + tokenValue);
		
		return execution.execute(request, body);
	}



	

}
