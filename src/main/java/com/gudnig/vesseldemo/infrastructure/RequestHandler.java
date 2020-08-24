package com.gudnig.vesseldemo.infrastructure;

public interface RequestHandler<TRequest, TResponse> {
    public TResponse handle(TRequest request);
}