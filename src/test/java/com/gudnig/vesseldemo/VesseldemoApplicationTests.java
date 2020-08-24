package com.gudnig.vesseldemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.time.ZonedDateTime;

import com.gudnig.vesseldemo.infrastructure.DomainException;
import com.gudnig.vesseldemo.position.PositionFormatRequest;
import com.gudnig.vesseldemo.position.PositionFormattingService;

@SpringBootTest
class VesseldemoApplicationTests {

	@Test
	void simplePositionFormatTest() {
		var handler = new PositionFormattingService();
		var request = new PositionFormatRequest();
		request.position.date = ZonedDateTime.now();
		request.position.latitude = 44.402392;
		request.position.longitude = -48.131009;
		request.position.speed = 18.5;
		request.vessel.name = "Séra Guðmundur";
		request.vessel.country = "IS";

		var result = handler.handle(request);
		assertThat(result.position.latitude).as("Latitude conversion should be close").isCloseTo(0.77496793616, Assertions.offset(0.0001));
		assertThat(result.position.longitude).as("Longitude conversion should be close").isCloseTo(-0.84004457935, Assertions.offset(0.0001));
		assertThat(result.position.speed).as("Speed conversion should be close").isCloseTo(9.517222, Assertions.offset(0.0001));
		assertThat(result.vessel.name).as("Names should match").isEqualTo("Séra Guðmundur");
	}

	@Test
	void throwOnBadLatitude() {
		var handler = new PositionFormattingService();
		var request = new PositionFormatRequest();
		request.position.date = ZonedDateTime.now();
		request.position.latitude = 144.402392;
		request.position.longitude = -248.131009;
		request.position.speed = 18.5;
		request.vessel.name = "Séra Guðmundur";
		request.vessel.country = "IS";

		assertThatThrownBy(() -> handler.handle(request)).isInstanceOf(DomainException.class).hasMessage("Longitude or latitude is out of possible values.");
	}

}
