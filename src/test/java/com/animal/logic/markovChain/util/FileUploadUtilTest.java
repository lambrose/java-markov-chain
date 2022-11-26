package com.animal.logic.markovChain.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class FileUploadUtilTest {

@Test
void ConvertEmptyFileContentsToStringTest() {
    MockMultipartFile sampleFile = new MockMultipartFile(
                "uploaded-file",
                "file-mock.txt",
                "text/plain",
                "".getBytes());

    assertThat(FileUploadUtil.convertFileToString(sampleFile)).isEqualTo("");
}

    @Test
    void ConvertTextFileContentsToStringTest() {
        MockMultipartFile sampleFile = new MockMultipartFile(
                "uploaded-file",
                "file-mock.txt",
                "text/plain",
                "This is the file content".getBytes());

        assertThat(FileUploadUtil.convertFileToString(sampleFile)).isEqualTo("This is the file content");
    }

    @Test
    void ConvertPDFFileContentsToStringTest() {
        MockMultipartFile sampleFile = new MockMultipartFile(
                "uploaded-file",
                "file-mock.pdf",
                "text/plain",
                "This is the file content".getBytes());

        assertThat(FileUploadUtil.convertFileToString(sampleFile)).isEqualTo("This is the file content");
    }
}
