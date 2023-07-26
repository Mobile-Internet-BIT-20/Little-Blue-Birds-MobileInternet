package com.LLLT.LittleBlueBirds.Enum;

public enum EnumResult {

    NONE (
            0,
            "None"
    ),
    EMAIL_INVALID (
            1,
            "Email Invalid"
    ),
    SUCCESS (
            2,
            "Success"
    ),
    WRONG_PASSWORD (
            3,
            "Wrong Password"
    ),
    USER_NOT_FOUND (
            4,
            "User not Found"
    ),
    FAILED (
            5,
            "Failed"
    ),
    UPLOAD_FAILED (
            6,
            "Upload Failed"
    ),
    UNSUPPORTED (
            7,
            "Unsupported"
    )
    ;

    private int    Code;
    private String Msg ;

    EnumResult (
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
