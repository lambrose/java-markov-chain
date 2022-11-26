package com.animal.logic.markovChain.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MarkovController.class)
public class MarkovControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void validMarkovTestOne() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "I went to the shop I am running now for the sweets".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSize", "1")
                        .param("outputSize", "8"))
                        .andExpect(status().is(200));
    }


    @Test
    public void validMarkovTestTwo() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "I went to the shop I went to the shop I went to the shop".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSize", "1")
                        .param("outputSize", "8"))
                        .andExpect(status().is(200));
    }

    @Test
    public void validMarkovTestThree() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                ("One of my favourite books is Harry Potter and the Philosopher's Stone by J.K. Rowling. It is a story " +
                        "about Harry Potter, an orphan brought up by his aunt and uncle because his parents were " +
                        "killed when he was a baby. Harry is unloved by his uncle and aunt but everything changes when" +
                        " he is invited to join Hogwarts School of Witchcraft and Wizardry and he finds out he's a " +
                        "wizard. At Hogwarts Harry realises he's special and his adventures begin when he and his new" +
                        " friends Ron and Hermione attempt to unravel the mystery of the Philosopher's Stone. I can " +
                        "read this book over and over again. From the very beginning until the end J.K. Rowling has me " +
                        "gripped! There is never a dull moment, whether it's battling with trolls, a three-headed dog, " +
                        "or Harry facing Lord Voldermort. I would definitely recommend this book because it keeps you " +
                        "reading without ever wanting to put the book down. By the end of the book you come to love" +
                        " the characters and you want to read more. You won't be disappointed because the second book " +
                        "in the series, Harry Potter and the Chamber of Secrets is just as great! If you haven't read " +
                        "any of the Harry Potter books you are missing out on the best series ever!").getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "3")
                        .param("suffixSize", "2")
                        .param("outputSize", "150"))
                        .andExpect(status().is(200));
    }

    @Test
    public void invalidFieldsMarkov404TestOne() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "I went to the shop I am running now for the sweets".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("outputSize", "8"))
                        .andExpect(status().is(404));
    }

    @Test
    public void invalidFieldsMarkov404TestTwo() throws Exception {
        String expected = "";
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "I went to the shop I am running now for the sweets".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSizes", "1")
                        .param("outputSize", "8"))
                .andExpect(status().is(404));
    }


    @Test
    public void invalidFieldsMarkov400Test() throws Exception {
        String expected = "";
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "I went to the shop I am running now for the sweets".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSize", "one")
                        .param("outputSize", "8"))
                        .andExpect(status().is(400));
    }

    @Test
    public void emptyFileMarkovTest() throws Exception {
        String expected = "";
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSize", "1")
                        .param("outputSize", "8"))
                .andExpect(status().is(400));
    }

    @Test
    public void emptyWithSpacesFileMarkovTest() throws Exception {
        String expected = "";
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "  ".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSize", "1")
                        .param("outputSize", "8"))
                .andExpect(status().is(400));
    }

    @Test
    public void suffixGreaterThanPrefixMarkovTest() throws Exception {
        String expected = "";
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "wizard. At Hogwarts Harry realises he's special and his adventures begin when he and his new".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSize", "3")
                        .param("outputSize", "8"))
                .andExpect(status().is(400));
    }

    @Test
    public void prefixGreaterThanOutputSizeMarkovTest() throws Exception {
        String expected = "";
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "wizard. At Hogwarts Harry realises he's special and his adventures begin when he and his new".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "20")
                        .param("suffixSize", "3")
                        .param("outputSize", "8"))
                .andExpect(status().is(400));
    }


    @Test
    public void FileSizeIsShortTest() throws Exception {
        String expected = "";
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "he and his new".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "5")
                        .param("suffixSize", "2")
                        .param("outputSize", "8"))
                .andExpect(status().is(400));
    }

    @Test
    public void invalidFileMarkovTest() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.jpeg",
                MediaType.TEXT_PLAIN_VALUE,
                "I went to the shop I went to the shop I went to the shop".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/algorithm/markov")
                        .file(file)
                        .param("prefixSize", "2")
                        .param("suffixSize", "1")
                        .param("outputSize", "8"))
                .andExpect(status().is(400));
    }
}
