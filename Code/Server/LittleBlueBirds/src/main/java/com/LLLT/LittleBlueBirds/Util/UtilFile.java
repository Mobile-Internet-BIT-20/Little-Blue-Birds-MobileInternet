package com.LLLT.LittleBlueBirds.Util;

import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UtilFile {

    interface _uploadPicture {

        UtilResult<String> Single (
                MultipartFile file      ,
                String        parentPath,
                String        targetPath,
                String        targetName
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
