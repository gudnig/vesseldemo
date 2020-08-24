package com.gudnig.vesseldemo.infrastructure;

public class DomainException extends RuntimeException  {

    /**
     *
     */
    private static final long serialVersionUID = -3263124591499182830L;

    public DomainException(String message) {
        super(message);
    }

    DomainException(String message, Throwable error) {
	    super(message, error);
	}
}