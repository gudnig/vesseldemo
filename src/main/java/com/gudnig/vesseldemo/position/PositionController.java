package com.gudnig.vesseldemo.position;

import com.gudnig.vesseldemo.infrastructure.DomainException;
import com.gudnig.vesseldemo.infrastructure.RequestHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

    private RequestHandler<PositionFormatRequest, FormattedPosition> formattingHandler;
    PositionController(RequestHandler<PositionFormatRequest, FormattedPosition> formattingHandler) {
        this.formattingHandler = formattingHandler;
    }

    @PostMapping("/position")
    public ResponseEntity<?> formatPosition(@RequestBody PositionFormatRequest request) {
        try {
            var result = formattingHandler.handle(request);
            return ResponseEntity.ok(result);
        } 
        catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }        
    }
}
