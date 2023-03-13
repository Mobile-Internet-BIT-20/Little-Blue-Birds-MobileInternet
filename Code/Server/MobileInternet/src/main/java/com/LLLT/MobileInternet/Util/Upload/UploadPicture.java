package com.LLLT.MobileInternet.Util.Upload;

import com.LLLT.MobileInternet.Util.Result.Result;
import com.LLLT.MobileInternet.Util.Result.ResultCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UploadPicture {

    public UploadPicture() {}

    public Result<String> onePicture(MultipartFile uploadPhoto, String uploadPath, String intermediate, String targetName) {
        String originalName   = uploadPhoto.getOriginalFilename();
        String suffixName     = originalName.substring(originalName.lastIndexOf("."));
        String returnName     = intermediate + targetName + suffixName;
        String fileName       = uploadPath + returnName;
        Result<String> result = new Result<>();
        new File(uploadPath + intermediate).mkdir();
        try {
            uploadPhoto.transferTo(new File(fileName));
            result.init(ResultCode.UPLOAD_SUCCESS, returnName);
        } catch (Exception e) {
            result.init(ResultCode.UPLOAD_FAILED);
        }
        return result;
    }
}
