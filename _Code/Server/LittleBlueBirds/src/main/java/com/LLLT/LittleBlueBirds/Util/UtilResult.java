package com.LLLT.LittleBlueBirds.Util;


import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import lombok.Data;

import java.io.Serializable;

@Data
public class UtilResult<T> implements Serializable {

    private int    Code;
    private String Msg ;
    private T      Data;

    public UtilResult () {}

    public UtilResult<T> init (
            EnumResult enumResult
    ) {

        return this.init(enumResult, null);
    }

    public UtilResult<T> init (
            EnumResult enumResult,
            T          Data
    ) {

        this.Code = enumResult.getCode();
        this.Msg  = enumResult.getMsg() ;
        this.Data = Data                ;

        return this;
    }
}
