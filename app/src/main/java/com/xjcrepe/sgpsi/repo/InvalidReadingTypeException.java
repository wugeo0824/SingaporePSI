package com.xjcrepe.sgpsi.repo;

/**
 * Created by LiXijun on 2017/9/10.
 */

public class InvalidReadingTypeException extends Exception {

    public static final String ERROR_MSG = "Invalid Readings Type";

    @Override
    public String getMessage() {
        return ERROR_MSG;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }
}
