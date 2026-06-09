package com.club.membership.exception;

public abstract class RemotePlatformException extends RuntimeException {
    protected RemotePlatformException(String message) {
        super(message);
    }
}
