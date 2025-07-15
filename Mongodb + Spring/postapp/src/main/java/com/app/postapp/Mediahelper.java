package com.app.postapp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Mediahelper {

    private Cloudinary cloudinary;

    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.load(); // Load Cloudinary credentials from .env
        cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    }

    /**
     * Upload multiple files (any type) to Cloudinary.
     * Ensures each file has a unique public_id by prefixing with timestamp.
     * Dots in map keys are replaced with underscores to avoid MongoDB issues.
     * @param files array of MultipartFile
     * @return Map<safe_public_id, secure_url>
     * @throws Exception if file exceeds 100MB or upload fails
     */
    public Map<String, String> uploadFiles(MultipartFile[] files) throws Exception {
        Map<String, String> uploaded = new HashMap<>();

        for (MultipartFile file : files) {
            if (file.getSize() > 100 * 1024 * 1024) {
                throw new Exception("Max file size: 100MB");
            }

            // Convert MultipartFile to temp java.io.File
            File tempFile = convertToFile(file);

            String publicId = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Map res = cloudinary.uploader().upload(
                    tempFile,
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "public_id", publicId
                    )
            );

            String originalPublicId = res.get("public_id").toString();
            String safeKey = originalPublicId.replace(".", "_"); // 🔧 sanitize key
            String secureUrl = res.get("secure_url").toString();

            uploaded.put(safeKey, secureUrl);

            tempFile.delete(); // cleanup
        }

        return uploaded;
    }

    // Helper method to convert MultipartFile to File
    private File convertToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload_", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

    /**
     * Delete a file from Cloudinary by its public_id.
     * Works for all file types.
     * @param publicId Cloudinary public ID
     * @return true if successfully deleted, false otherwise
     */
    public boolean deleteFile(String publicId) {
        try {
            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap("resource_type", "auto")
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Log error
            return false;
        }
    }
}
