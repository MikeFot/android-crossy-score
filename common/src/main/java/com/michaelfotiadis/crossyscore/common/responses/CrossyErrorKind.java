package com.michaelfotiadis.crossyscore.common.responses;

/**
 */
public enum CrossyErrorKind {
    COMMUNICATION,
    UNEXPECTED,
    NO_NETWORK,
    NO_LOCATION,
    ERROR_ACCESSING_LOCAL_STORAGE,
    ERROR_RETRIEVING_FROM_CACHE,
    IO_EXCEPTION,
    REQUEST_FAILED,
    NOT_FOUND,
    DESERIALIZATION_ERROR,
    SERVER,
    INVALID_REQUEST_PARAMETERS,
    INVALID_CONTENT,
    NO_CONTENT_RETURNED
}