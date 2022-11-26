package com.animal.logic.markovChain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkovChainResponse {
    private String fileName;
    private int outputSize;
    private String outputText;
}
