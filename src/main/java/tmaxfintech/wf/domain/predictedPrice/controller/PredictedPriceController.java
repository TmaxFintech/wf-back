package tmaxfintech.wf.domain.predictedPrice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tmaxfintech.wf.domain.predictedPrice.dto.PredictedPriceRequestDto;
import tmaxfintech.wf.domain.predictedPrice.service.PredictedPriceService;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class PredictedPriceController {

    private final PredictedPriceService predictedPriceService;

    public PredictedPriceController(PredictedPriceService predictedPriceService) {
        this.predictedPriceService = predictedPriceService;
    }

    @PutMapping("/coins/predict")
    public ResponseEntity<DefaultResponse> updatePredictedPrice(@RequestBody PredictedPriceRequestDto predictedPriceRequestDto) {
//        System.out.println(predictedPriceRequestDto);
        return predictedPriceService.updatePredictedPrice(predictedPriceRequestDto.getSymbol(), predictedPriceRequestDto.getPredictedPrice());
    }
}
