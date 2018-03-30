package org.cats.util;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.cats.stock.controller.DispatchItemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class URLBuilder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DispatchItemController.class);
	
	@Value("${cats.api.url}")
	private String catsApiUrl;
	
	public URI getFdpIdsByRegion(Integer regionId){
		System.out.println("Request URI: "+catsApiUrl);
		return URI.create(catsApiUrl.concat(String.valueOf(regionId))) ;
	}

	public URI getOperations(){
		return URI.create(catsApiUrl.concat("/operations"));
	}

}
