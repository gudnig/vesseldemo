package com.gudnig.vesseldemo;

import com.gudnig.vesseldemo.decorators.LoggingRequestHandlerDecorator;
import com.gudnig.vesseldemo.decorators.ValidationRequestHandlerDecorator;
import com.gudnig.vesseldemo.infrastructure.RequestHandler;
import com.gudnig.vesseldemo.position.FormattedPosition;
import com.gudnig.vesseldemo.position.PositionFormatRequest;
import com.gudnig.vesseldemo.position.PositionFormatRequestValidator;
import com.gudnig.vesseldemo.position.PositionFormattingService;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class VesseldemoApplication {
	@Bean
    RequestHandler<PositionFormatRequest, FormattedPosition> decoratedFormattedPositionHandler() {
		var service = new PositionFormattingService();
		return new LoggingRequestHandlerDecorator<>(
			LoggerFactory.getLogger(service.getClass()), 
			new ValidationRequestHandlerDecorator<PositionFormatRequest, FormattedPosition>(
				new PositionFormatRequestValidator(), 
				service ));
	}

	public static void main(String[] args) {
		SpringApplication.run(VesseldemoApplication.class, args);
	}

}
