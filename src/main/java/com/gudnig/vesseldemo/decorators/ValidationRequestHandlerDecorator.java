package com.gudnig.vesseldemo.decorators;

import com.gudnig.vesseldemo.infrastructure.RequestHandler;
import com.gudnig.vesseldemo.infrastructure.Validator;

public class ValidationRequestHandlerDecorator<TRequest, TResult> implements RequestHandler<TRequest, TResult> {

    public ValidationRequestHandlerDecorator(Validator<TRequest> validator, RequestHandler<TRequest, TResult> decoratee) {
        this.validator = validator;
        this.decoratee = decoratee;
    }
    private Validator<TRequest> validator;
    private RequestHandler<TRequest, TResult> decoratee;

    @Override
    public TResult handle(TRequest request) {
        // This throws on invalid request
        this.validator.Validate(request);
        return decoratee.handle(request);
    }
    
}