package com.LLLT.MobileInternet.Util.Sex;


public enum SexList {

    NONE(0),
    MALE(1),
    FEMALE(2);

    private int Code;
    SexList(int Code) {
        this.Code = Code;
    }

    public int getCode() {
        return Code;
    }
}
