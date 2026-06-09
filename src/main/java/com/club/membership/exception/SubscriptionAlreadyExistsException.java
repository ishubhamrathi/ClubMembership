package com.club.membership.exception;

public class SubscriptionAlreadyExistsException extends RemotePlatformException {
    public SubscriptionAlreadyExistsException(String message) {
        super(message);
    }
}
