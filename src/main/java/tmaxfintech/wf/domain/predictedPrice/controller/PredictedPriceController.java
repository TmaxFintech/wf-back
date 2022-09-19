package tmaxfintech.wf.domain.predictedPrice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmaxfintech.wf.domain.predictedPrice.dto.PredictedPriceRequestDto;
import tmaxfintech.wf.domain.predictedPrice.service.PredictedPriceService;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class PredictedPriceController {

    private final PredictedPriceService predictedPriceService;

    @Value("${responseMessage.PREDICTED_PRICE_GET_SUCCESS}")
    private String PREDICTED_PRICE_GET_SUCCESS;

    public PredictedPriceController(PredictedPriceService predictedPriceService) {
        this.predictedPriceService = predictedPriceService;
    }

    @PutMapping("/coins/predict")
    public ResponseEntity<DefaultResponse> updatePredictedPrice(@RequestBody PredictedPriceRequestDto predictedPriceRequestDto) {
        return predictedPriceService.updatePredictedPrice(predictedPriceRequestDto.getSymbol(), predictedPriceRequestDto.getPredictedPrice(), predictedPriceRequestDto.getIntervals());
    }

    @GetMapping("/coins/predict")
    public ResponseEntity<DefaultResponse> getPredictedPrice(@RequestParam(required = false) String symbol, @RequestParam String intervals) {
        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), PREDICTED_PRICE_GET_SUCCESS, predictedPriceService.getPredictedPrice(symbol, intervals)), HttpStatus.OK);
    }
}
