package com.gudnig.vesseldemo.position;

import java.time.ZonedDateTime;
import com.gudnig.vesseldemo.infrastructure.DomainException;
import com.gudnig.vesseldemo.infrastructure.RequestHandler;


public class PositionFormattingService implements RequestHandler<PositionFormatRequest, FormattedPosition> {

	@Override
	public FormattedPosition handle(PositionFormatRequest request) {

		// long should be between -180deg to 180deg and lat between -90 and 90 
		if(request.position.latitude > 180.0 || 
			request.position.latitude < -180.0 || 
			request.position.longitude > 90.0 ||
			request.position.longitude < -90.0) {
				throw new DomainException("Longitude or latitude is out of possible values.");
			}

		FormattedPosition formatted = new FormattedPosition();
		formatted.vessel.name = request.vessel.name;
		formatted.position.date = request.position.date;
		formatted.position.latitude = degreesToRadians(request.position.latitude);
		formatted.position.longitude = degreesToRadians(request.position.longitude);
		formatted.position.speed = request.position.speed / 1.943844; // speed to meters per second using the international nautical mile
		formatted.position.receivedDate = ZonedDateTime.now();

		return formatted;
	}

	private double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180;
	}

}