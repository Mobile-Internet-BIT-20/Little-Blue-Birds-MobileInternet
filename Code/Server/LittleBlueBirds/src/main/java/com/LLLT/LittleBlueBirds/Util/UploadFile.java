package com.LLLT.LittleBlueBirds.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UploadFile {

    interface picture {

        Result<String> Single(MultipartFile multipartFile, String UploadPath, String Intermediate, String TargetName);
    }

    public static final class UploadPicture implements picture {

        @Override
        public Result<String> Single(MultipartFile multipartFile, String UploadPath, String Intermediate, String TargetName) {

            Result<String> result = new Result<>();

            String OriginalName = multipartFile.getOriginalFilename();
            assert OriginalName != null;

            String SuffixName = OriginalName.substring(OriginalName.lastIndexOf("."));
            String ReturnName = Intermediate + TargetName + SuffixName;
            String FileName   = UploadPath + ReturnName;

            new File(UploadPath + Intermediate).mkdir();

            try {

                multipartFile.transferTo(new File(FileName));
                return result.init(Result.CodeEnum.SUCCESS, ReturnName);

            } catch (Exception e) {

                System.out.println(e);
                return result.init(Result.CodeEnum.UPLOAD_FAILED);
            }
        }
    }
}
