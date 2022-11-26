package com.animal.logic.markovChain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class MarkovChainRequest {
    @NotNull
    private MultipartFile file;

    @NotNull
    @Min(2)
    private int prefixSize;

    @NotNull
    @Min(1)
    private int suffixSize;

    @NotNull
    @Min(3)
    private int outputSize;
}