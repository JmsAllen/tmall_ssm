package com.how2java.tmall.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Allen
 */
public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
