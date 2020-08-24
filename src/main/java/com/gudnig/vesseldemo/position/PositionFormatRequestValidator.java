package com.gudnig.vesseldemo.position;

import java.util.ArrayList;

import javax.validation.ValidationException;

import com.gudnig.vesseldemo.infrastructure.Validator;

import org.apache.commons.lang3.StringUtils;

public class PositionFormatRequestValidator implements Validator<PositionFormatRequest> {

    @Override
    public void Validate(PositionFormatRequest request) {
        var errors = new ArrayList<String>();
        if(request.position == null) {
            errors.add("Position is required.");
        }
        else {
            if(request.position.date == null) {
                errors.add("Date is required.");
            }
            if(request.position.latitude == null) {
                errors.add("Latitude is required.");
            }
            if(request.position.longitude == null) {
                errors.add("Longitude is required.");
            }
            if(request.position.speed == null) {
                errors.add("Speed is required.");
            }
        }
        if(request.vessel == null) {
            errors.add("Vessel is required.");
        }
        else if(StringUtils.isEmpty(request.vessel.name)) {
            errors.add("Name is required.");
        }
        if(!errors.isEmpty()) {
            throw new ValidationException(StringUtils.join(errors, " "));
        }
    }
    
}