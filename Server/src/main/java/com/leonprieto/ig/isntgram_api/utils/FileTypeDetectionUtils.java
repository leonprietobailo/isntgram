package com.leonprieto.ig.isntgram_api.utils;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileTypeDetectionUtils {

  private static final Tika TIKA = new Tika();
  private static final List<String> ALLOWED_TYPES =
      Arrays.asList("PNG", "JPEG", "JPG", "GIF", "BMP");

  public static String detectFileExtension(MultipartFile file) {
    try {
      final String mimeType = TIKA.detect(file.getInputStream());
      return mimeTypeToExtension(mimeType);
    } catch (IOException e) {
      throw new RuntimeException("Internal server error", e);
    }
  }

  private static String mimeTypeToExtension(String mimeType) throws IllegalArgumentException {
    return switch (mimeType) {
      case "image/jpeg", "image/jpg" -> "jpeg";
      case "image/gif" -> "gif";
      case "image/bmp" -> "bmp";
      case "image/png" -> "png";
      case "image/tiff" -> "tiff";
      default -> throw new IllegalArgumentException("Unsupported mime type: " + mimeType);
    };
  }
}
