package com.animal.logic.markovChain.util;

import java.io.*;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
//    Create a bufferReader to read the file contents and return a string
    public static String convertFileToString(MultipartFile multipartFile)  {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) stringBuilder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
//      remove whitespace
        return stringBuilder.toString().trim();
    }

}