package com.gudnig.vesseldemo.decorators;

import javax.validation.ValidationException;

import com.gudnig.vesseldemo.infrastructure.DomainException;
import com.gudnig.vesseldemo.infrastructure.RequestHandler;

import org.slf4j.Logger;

public class LoggingRequestHandlerDecorator<TRequest, TResult> implements RequestHandler<TRequest, TResult> {

    private RequestHandler<TRequest, TResult> decoratee;
    private Logger logger;

    public LoggingRequestHandlerDecorator(Logger logger, RequestHandler<TRequest, TResult> decoratee) {
        this.decoratee = decoratee;
        this.logger = logger;
    }

    @Override
    public TResult handle(TRequest request) {

        this.logger.info("Handling request " + request.getClass().getSimpleName());
        try {
            var result = decoratee.handle(request);
            this.logger.info("Request handled with result " + result.getClass().getSimpleName());
            return result;
        }

        catch(ValidationException | DomainException e) {
            this.logger.warn("Domain or validation error " + e.getMessage());
            throw e;
        }
        catch(Exception e) {
            this.logger.error(e.getMessage(), e);
            throw e;
        }
    }
    
}