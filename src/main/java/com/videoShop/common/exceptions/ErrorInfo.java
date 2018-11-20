package com.videoShop.common.exceptions;

/**
 * Error informations that will be converted to JSON Message
 */
public class ErrorInfo {
    public final String url;
    public final String[] messages;

    public ErrorInfo(String url, String... messages) {
        this.url = url;
        this.messages = messages;
    }
}