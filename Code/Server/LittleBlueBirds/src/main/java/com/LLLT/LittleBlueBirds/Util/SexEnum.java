package com.LLLT.LittleBlueBirds.Util;

public enum SexEnum {

    NONE(0),
    MALE(1),
    FEMALE(2),
    ;

    private int Code;
    SexEnum(int Code) {
        this.Code = Code;
    }

    public int getCode() {
        return Code;
    }
}
