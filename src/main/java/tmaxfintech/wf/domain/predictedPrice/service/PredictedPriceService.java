package tmaxfintech.wf.domain.predictedPrice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmaxfintech.wf.domain.predictedPrice.dto.PredictedPriceResponseDto;
import tmaxfintech.wf.domain.predictedPrice.entity.PredictedPrice;
import tmaxfintech.wf.domain.predictedPrice.repository.PredictedPriceRepository;
import tmaxfintech.wf.util.response.DefaultResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredictedPriceService {

    private final PredictedPriceRepository predictedPriceRepository;

    @Value("${responseMessage.PREDICTED_PRICE_UPDATE_SUCCESS}")
    private String PREDICTED_PRICE_UPDATE_SUCCESS;

    public PredictedPriceService(PredictedPriceRepository predictedPriceRepository) {
        this.predictedPriceRepository = predictedPriceRepository;
    }

    @Transactional
    public ResponseEntity<DefaultResponse> updatePredictedPrice(String symbol, Double predictedPrice,String intervals) {

        PredictedPrice predictedprice = predictedPriceRepository.findBySymbolAndIntervals(symbol, intervals).get();
        predictedprice.updatePredictedPrice(symbol,predictedPrice, intervals);

        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), PREDICTED_PRICE_UPDATE_SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public List<PredictedPriceResponseDto> getPredictedPrice(String symbol, String intervals) {
        if (symbol == null) {
            return predictedPriceRepository.findByIntervals(intervals).stream().map(PredictedPrice::toDto).collect(Collectors.toList());
        }
        return Arrays.asList(predictedPriceRepository.findBySymbolAndIntervals(symbol, intervals).get().toDto());
    }
}
