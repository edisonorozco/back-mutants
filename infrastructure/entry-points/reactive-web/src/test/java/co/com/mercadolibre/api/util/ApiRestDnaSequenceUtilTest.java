package co.com.mercadolibre.api.util;

import co.com.mercadolibre.api.request.ApiRequestDnaSequence;
import co.com.mercadolibre.api.response.ApiResponseDnaSequence;
import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.specie.response.StatsUC;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ApiRestDnaSequenceUtilTest {
    private final ApiRestDnaSequenceUtil apiRestDnaSequenceUtil = new ApiRestDnaSequenceUtil();

    @Test
    void apiRequestToUseCaseRequestTest() {
        Assertions.assertNotNull(apiRestDnaSequenceUtil.apiRequestToUseCaseRequest(
                ApiRequestDnaSequence.builder().build()));
    }

    @Test
    void useCaseResponseToApiResponseTest() {
        Assertions.assertNotNull(apiRestDnaSequenceUtil.useCaseResponseToApiResponse(
                DnaSequenceResponseUC.builder().build()));
    }

    @Test
    void apiResponseDnaSequenceResponseEntity() {
        Assertions.assertNotNull(apiRestDnaSequenceUtil.apiResponseDnaSequenceResponseEntity(
                ApiResponseDnaSequence.builder()
                        .mutant(true)
                        .build()));
    }

    @Test
    void statsUCToEntityApiResponseStatTest() {
        Assertions.assertNotNull(apiRestDnaSequenceUtil.statsUCToEntityApiResponseStat(
                StatsUC.builder().build()));
    }

}
