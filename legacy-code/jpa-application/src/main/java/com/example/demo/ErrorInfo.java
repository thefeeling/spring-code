package com.example.demo;

/**
 * Created by Daniel on 2017. 7. 9..
 */
public class ErrorInfo {
    private int code;
    private String name;
    private String message;

    public ErrorInfo(int code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ErrorInfo setCode(int code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public ErrorInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
            "code=" + code +
            ", name='" + name + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
