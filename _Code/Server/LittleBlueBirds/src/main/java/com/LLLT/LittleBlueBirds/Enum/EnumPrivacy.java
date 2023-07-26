package com.LLLT.LittleBlueBirds.Enum;

public enum EnumPrivacy {

    PUBLIC (
            0,
            "Public"
    ),
    PRIVATE (
            1,
            "Private"
    ),
    FRIENDS (
            2,
            "Friends"
    ),
    LOGIN_USER (
            3,
            "Login User"
    )
    ;

    private int    Code;
    private String Msg ;

    EnumPrivacy (
            int    Code,
            String Msg
    ) {
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
