package com.carlosrey.springboot.backend.apirest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestClientConfig   {
	
	@Value("${base.url}")
	String baseUrl;
	
	@Bean("webclient")
	public WebClient webclient() {
		
		return  WebClient.builder()
		        .baseUrl(baseUrl)
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		        .filter(logRequest("peticion"))
		        .build();
		
	}

	private ExchangeFilterFunction logRequest(String string) {
		// TODO Auto-generated method stub
		return (clientRequest, next) -> {
			System.out.println(string + "{}" + clientRequest.method());
			return null;
		};
		};
	}
