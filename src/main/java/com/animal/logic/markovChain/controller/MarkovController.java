package com.animal.logic.markovChain.controller;

import com.animal.logic.markovChain.dto.MarkovChainRequest;
import com.animal.logic.markovChain.dto.MarkovChainResponse;
import com.animal.logic.markovChain.exception.InvalidInputException;
import com.animal.logic.markovChain.service.ExceptionService;
import com.animal.logic.markovChain.service.MarkovService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/algorithm")
public class MarkovController {

    @PostMapping(value="/markov", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<MarkovChainResponse> markovTextGenerator(@Valid @ModelAttribute MarkovChainRequest request, BindingResult bindingResult) {
//        Client-side validation
        if (bindingResult.hasErrors())
            ExceptionService.ExceptionHandler(bindingResult.getFieldErrors().get(0).getRejectedValue());
//        Generate text
        String newTextGenerated = MarkovService.textGenerator(request);

        if(!Objects.requireNonNull(request.getFile().getOriginalFilename()).contains(".txt")) {
            throw new InvalidInputException("Invalid File type. Must be a text file");
        }
//        get filename
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(request.getFile().getOriginalFilename()));

//      success response
        MarkovChainResponse response = new MarkovChainResponse();
        response.setFileName(fileName);
        response.setOutputSize(newTextGenerated.length());
        response.setOutputText(newTextGenerated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
