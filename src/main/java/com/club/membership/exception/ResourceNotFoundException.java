package com.club.membership.exception;

public class ResourceNotFoundException extends RemotePlatformException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}