package com.LLLT.LittleBlueBirds.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FileOP {

    interface picture {

        Result<String> Single (MultipartFile multipartFile, String UploadPath, String Intermediate, String TargetName);
        Result<List<String>> Multiple (MultipartFile[] multipartFiles, String UploadPath, String Intermediate);
    }

    interface drop {

        void AllSub (String Path);
    }

    interface judge {

        boolean isPicture (MultipartFile multipartFile);
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

            new java.io.File(UploadPath + Intermediate).mkdirs();

            try {

                multipartFile.transferTo(new java.io.File(FileName));
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

                if (!new JudgeFile().isPicture(mfs)) {

                    return result.init(Result.CodeEnum.UPLOAD_FAILED);
                }

                Result<String> tmp = Single(mfs, UploadPath, Intermediate, UUID.randomUUID().toString());

                if (tmp.getCode() != Result.CodeEnum.SUCCESS.getCode()) {
                    return result.init(Result.CodeEnum.UPLOAD_FAILED);
                }

                ImgList.add(tmp.getData());
            }

            return result.init(Result.CodeEnum.SUCCESS, ImgList);
        }
    }

    public static final class DropFile implements drop {

        @Override
        public void AllSub(String Path) {

            File file = new File(Path);
            File[] files = file.listFiles();

            if (files == null) {
                return;
            }

            for (File F : files) {

                if (F.isDirectory()) {

                    AllSub(F.getPath());
                    continue;
                }

                F.delete();
            }

            file.delete();
        }
    }

    public static final class JudgeFile implements judge {

        @Override
        public boolean isPicture(MultipartFile multipartFile) {

            String OriName = multipartFile.getOriginalFilename();
            String Suffix  = OriName.substring(OriName.lastIndexOf("."));

            switch (Suffix) {

                case "jpg" :

                case "png":

                case "jpeg":
                    return true;

                default:
                    return false;
            }
        }
    }
}
