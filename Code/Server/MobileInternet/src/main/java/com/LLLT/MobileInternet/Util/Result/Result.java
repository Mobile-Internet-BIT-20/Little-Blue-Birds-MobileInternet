package com.LLLT.MobileInternet.Util.Result;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private int    resultCode;
    private String resultMsg ;
    private T      Data      ;

    public int getResultCode() { return resultCode; }
    public String getResultMsg() { return resultMsg; }
    public T getData() { return Data; }

    public void setResultCode(int resultCode) { this.resultCode = resultCode; }
    public void setResultMsg(String resultMsg) { this.resultMsg = resultMsg; }
    public void setData(T data) { Data = data; }

    public Result(){}
    public void init(ResultCode resultCode) {
        init(resultCode, null);
    }
    public void init(ResultCode resultCode, T data) {
        this.resultCode = resultCode.getCode();
        this.resultMsg  = resultCode.getMsg() ;
        this.Data       = data;
    }
}
