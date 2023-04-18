package com.LLLT.LittleBlueBirds.Util;

import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UtilFile {

    interface _uploadPicture {

        UtilResult<String> Single (
                MultipartFile file      ,
                String        parentPath,
                String        targetPath,
                String        targetName
        );

        UtilResult<List<String>> Multiple (
                MultipartFile[] files     ,
                String          parentPath,
                String          targetPath
        );
    }

    interface _dropFile {

        void AllSub (
                String path
        );
    }

    interface _formatJudge {

        String FormatPicture (
                String Suffix
        );
    }

    public static final class UploadPicture implements _uploadPicture {

        @Override
        public UtilResult<String> Single(
                MultipartFile file      ,
                String        path      ,
                String        targetPath,
                String        targetName
        ) {

            UtilResult<String> result = new UtilResult<>();

            String oriName = file.getOriginalFilename();

            assert oriName != null;
            String  Suffix  = new FormatJudge().FormatPicture(oriName.substring(oriName.lastIndexOf(".")));

            if (Suffix.equals(EnumResult.UNSUPPORTED.getMsg())) {

                return result.init(EnumResult.UNSUPPORTED);
            }

            String PathRelative = targetPath + "/" + targetName + Suffix;
            String PathPhysics  = path + PathRelative;

            new File(path + targetPath).mkdirs();

            try {

                file.transferTo(new File(PathPhysics));

                return result.init(EnumResult.SUCCESS, PathRelative);
            } catch (Exception e) {

                System.err.println(e);
                return result.init(EnumResult.FAILED);
            }
        }

        @Override
        public UtilResult<List<String>> Multiple (
                MultipartFile[] files,
                String parentPath    ,
                String targetPath
        ) {

            UtilResult<List<String>> result = new UtilResult<>();

            List<String> ImgList = new ArrayList<>(Collections.emptyList());

            for (MultipartFile mfs : files) {

                UtilResult<String> tmpResult = Single(mfs, parentPath, targetPath, UUID.randomUUID().toString());

                if (tmpResult.getCode() != EnumResult.SUCCESS.getCode()) {

                    return result.init(EnumResult.FAILED);
                }

                ImgList.add(tmpResult.getData());
            }

            return result.init(EnumResult.SUCCESS, ImgList);
        }
    }

    public static final class DropFile implements _dropFile {

        @Override
        public void AllSub (
                String path
        ) {

            File   file  = new File(path);
            File[] files = file.listFiles();

            if (files == null) {

                return;
            }

            for (File f : files) {

                if (f.isDirectory()) {

                    AllSub(f.getPath());
                    continue;
                }

                f.delete();
            }

            file.delete();
        }
    }

    public static final class FormatJudge implements _formatJudge {

        @Override
        public String FormatPicture(
                String Suffix
        ) {

            switch (Suffix) {

                case "jpg":
                    return "jpg";

                case "jpeg":
                    return "jpeg";

                case "png":
                    return "png";

                default:
                    return EnumResult.UNSUPPORTED.getMsg();
            }
        }
    }
}
