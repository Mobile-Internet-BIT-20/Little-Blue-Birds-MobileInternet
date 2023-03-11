package com.LLLT.MobileInternet.Util.Result;

public enum ResultCode {

    EMAIL_EXISTS(-1, "Email Exists"),
    EMAIL_INVALID(-2, "Email Invalid"),
    WRONG_PASSWORD(-3, "Wrong Password"),
    USER_NOT_FOUND(-4, "User Not Found"),
    ACCOUNT_DELETE_FAILED(-5, "Delete Failed"),
    UPLOAD_FAILED(-6, "Upload Failed"),
    NONE(0, "None"),
    REGISTER_SUCCESS(1, "Register Success"),
    LOGIN_SUCCESS(2, "Login Success"),
    DELETE_SUCCESS(3, "Delete Success"),
    UPDATE_SUCCESS(4, "Update Success"),
    UPLOAD_SUCCESS(5, "Upload Success"),
    ;

    private final int    code;
    private final String msg ;
    ResultCode(int code, String msg) {

        this.code = code;
        this.msg  = msg ;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
