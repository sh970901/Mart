package com.hun.market.backoffice.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadImage(MultipartFile file) throws IOException;

}
