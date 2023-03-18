package com.LLLT.LittleBlueBirds.Util;

import java.io.Serializable;

public class Result<T> implements Serializable {

    public enum CodeEnum {

        NONE(0, "None"),
        EMAIL_INVALID(1, "Email Invalid"),
        SUCCESS(2, "Success"),
        FAILED(3, "Failed"),
        WRONG_PASSWORD(4, "Wrong Password"),
        USER_NOT_FOUND(5, "User Not Found"),
        UPLOAD_FAILED(6, "Upload Failed"),
        ;

        private int    Code;
        private String Msg ;

        CodeEnum (int Code, String Msg) {
            this.Code = Code;
            this.Msg  = Msg ;
        }

        public int getCode() {
            return Code;
        }

        public String getMsg() {
            return Msg;
        }
    }

    private Integer Code;
    private String  Msg ;
    private T       Data;

    public Result () {}

    public Result<T> init (Result.CodeEnum Code) {
        return this.init(Code, null);
    }

    public Result<T> init (Result.CodeEnum Code, T Data) {
        this.Code = Code.getCode();
        this.Msg  = Code.getMsg();
        this.Data = Data;
        return this;
    }

    // Getter and Setter

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
