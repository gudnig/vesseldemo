package com.gudnig.vesseldemo;

import com.gudnig.vesseldemo.infrastructure.RequestHandler;
import com.gudnig.vesseldemo.position.FormattedPosition;
import com.gudnig.vesseldemo.position.PositionFormatRequest;
import com.gudnig.vesseldemo.position.PositionFormattingService;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VesseldemoApplication {
	@Bean
    RequestHandler<PositionFormatRequest, FormattedPosition> decoratedFormattedPositionHandler() {
		return new PositionFormattingService();
	}

	public static void main(String[] args) {
		SpringApplication.run(VesseldemoApplication.class, args);
	}

}
