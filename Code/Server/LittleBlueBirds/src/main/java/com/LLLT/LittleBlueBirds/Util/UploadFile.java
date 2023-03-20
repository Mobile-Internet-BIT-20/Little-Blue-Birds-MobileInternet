package com.LLLT.LittleBlueBirds.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UploadFile {

    interface picture {

        Result<String> Single (MultipartFile multipartFile, String UploadPath, String Intermediate, String TargetName);
        Result<List<String>> Multiple (MultipartFile[] multipartFiles, String UploadPath, String Intermediate);
    }

    public static final class UploadPicture implements picture {

        @Override
        public Result<String> Single(MultipartFile multipartFile, String UploadPath, String Intermediate, String TargetName) {

            Result<String> result = new Result<>();

            String OriginalName = multipartFile.getOriginalFilename();
            assert OriginalName != null;

            String SuffixName = OriginalName.substring(OriginalName.lastIndexOf("."));
            String ReturnName = Intermediate + "/" + TargetName + SuffixName;
            String FileName   = UploadPath + ReturnName;

            new File(UploadPath + Intermediate).mkdirs();

            try {

                multipartFile.transferTo(new File(FileName));
                return result.init(Result.CodeEnum.SUCCESS, ReturnName);

            } catch (Exception e) {

                System.out.println(e);
                return result.init(Result.CodeEnum.UPLOAD_FAILED);
            }
        }

        @Override
        public Result<List<String>> Multiple(MultipartFile[] multipartFiles, String UploadPath, String Intermediate) {

            List<String> ImgList = new java.util.ArrayList<>(Collections.emptyList());
            Result<List<String>> result = new Result<>();

            for (MultipartFile mfs : multipartFiles) {

                Result<String> tmp = Single(mfs, UploadPath, Intermediate, UUID.randomUUID().toString());

                if (tmp.getCode() != Result.CodeEnum.SUCCESS.getCode()) {
                    return result.init(Result.CodeEnum.UPLOAD_FAILED);
                }

                ImgList.add(tmp.getData());
            }

            return result.init(Result.CodeEnum.SUCCESS, ImgList);
        }
    }
}
