package com.LLLT.LittleBlueBirds.Util;

public enum PrivacyEnum {

    PUBLIC(0, "SVisible to all"),
    PRIVATE(1, "Visible only to yourself"),
    FOLLOWER(2, "Visible only to follower"),
    FRIEND(3, "Visible only to Friends"),
    ;

    private int Code;
    private String Description;

    PrivacyEnum(int Code, String Description) {
        this.Code        = Code       ;
        this.Description = Description;
    }

    public int getCode() {
        return Code;
    }

    public String getDescription() {
        return Description;
    }
}
