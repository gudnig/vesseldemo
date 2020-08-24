package com.gudnig.vesseldemo.infrastructure;

public interface Validator<TRequest> {
    public void Validate(TRequest request);
}