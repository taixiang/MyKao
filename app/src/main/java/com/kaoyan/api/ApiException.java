package com.kaoyan.api;

/**
 * Created by tx on 2017/7/31.
 */

public class ApiException extends RuntimeException {

    public ApiException(int status) {

        super(getApiStatus(status));
    }

    private static String getApiStatus(int status){

        return "";
    }
}
