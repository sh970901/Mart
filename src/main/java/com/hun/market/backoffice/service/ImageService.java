package com.hun.market.backoffice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface ImageService {
    void uploadImage(MultipartFile file) throws IOException;

    void uploadImages(ArrayList<MultipartFile> file) throws IOException;
}
