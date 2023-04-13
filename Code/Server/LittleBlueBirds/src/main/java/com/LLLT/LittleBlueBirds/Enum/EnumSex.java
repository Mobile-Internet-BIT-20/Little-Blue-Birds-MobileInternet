package com.LLLT.LittleBlueBirds.Enum;

public enum EnumSex {

    NONE (
            0,
            "None"
    ),
    MALE (
            1,
            "Male"
    ),
    FEMALE (
            2,
            "Female"
    )
    ;

    private int    Code;
    private String Msg ;

    EnumSex (
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
