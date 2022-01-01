package com.lee.util;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 上传文件的工具类
 */
public class UploadFile {

    // 存储文件上传失败的错误信息
    private static Map<String, Object> errorResult = new HashMap<>();
    // 存储头像的上传结果信息
    private static Map<String, Object> uploadResult = new HashMap<>();

    /**
     *  (提取公共代码 : 提高代码的可重用性)获取头像的上传结果信息
     * @param photo
     * @param dirPath
     * @param portraitPath
     * @return
     */
    public static Map<String, Object> getUploadResult(MultipartFile photo, String dirPath, String portraitPath) {
        if (!photo.isEmpty() && photo.getSize() > 0) {
            // 获取图片的原始名称
            String originalName = photo.getOriginalFilename();
            // 上传图片,error_result:存储头像上传失败的错误信息
            errorResult  = com.lee.util.UploadFile.uploadPhoto(photo, dirPath);
            if (errorResult != null) {
                return errorResult;
            }
            // 使用UUID重命名图片名称(uuid__原始图片名称)
            String newPhotoName = UUID.randomUUID() + "__" + originalName;
            // 将上传的文件保存到目标目录下
            try {
                photo.transferTo(new File(dirPath + newPhotoName));
                uploadResult.put("success", true);
                uploadResult.put("portraitPath", portraitPath + newPhotoName);
            } catch (IOException e) {
                e.printStackTrace();
                uploadResult.put("success", false);
                uploadResult.put("msg", "上传文件失败! 服务器端发生异常!");
                return uploadResult;
            }
        }else {
            uploadResult.put("success", false);
            uploadResult.put("msg", "头像上传失败! 未找到指定图片!");
        }
        return uploadResult;
    }

    /**
     * 效验所上传图片的大小及格式等信息
     * @param photo
     * @param path
     * @return
     */
    private static Map<String, Object> uploadPhoto(MultipartFile photo, String path) {
        // 限制头像大小(20M)
        int MAX_SIZE = 20971520;
        // 获取图片的原始名称
        String originalName = photo.getOriginalFilename();
        // 如果保存文件的路径不存在,则创建该目录
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        // 限制上传文件的大小
        if (photo.getSize() > MAX_SIZE) {
            errorResult.put("success", false);
            errorResult.put("msg", "上传的图片大小不能超过20M哟!");
            return errorResult;
        }
        // 限制上传的文件类型
        String[] suffixs = new String[] {".png", ".PNG", ".jpg", ".JPG", ".jepg", ".JEPG", ".gif", ".GIF", ".bmp", ".BMP"};
        SuffixFileFilter suffixFileFilter = new SuffixFileFilter(suffixs);
        if (!suffixFileFilter.accept(new File(path + originalName))) {
            errorResult.put("success", false);
            errorResult.put("msg", "禁止上传此类型文件! 请上传图片哟!");
            return errorResult;
        }

        return null;
    }
}
