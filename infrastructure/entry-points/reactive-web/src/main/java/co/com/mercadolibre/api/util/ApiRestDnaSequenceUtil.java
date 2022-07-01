package co.com.mercadolibre.api.util;

import co.com.mercadolibre.api.request.ApiRequestDnaSequence;
import co.com.mercadolibre.api.response.ApiResponseDnaSequence;
import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ApiRestDnaSequenceUtil {

    public DnaSequence apiRequestToUseCaseRequest(ApiRequestDnaSequence apiRequestDnaSequence) {
        return DnaSequence.builder()
                .dna(apiRequestDnaSequence.getDna())
                .build();
    };

    public ApiResponseDnaSequence useCaseResponseToApiResponse(DnaSequenceResponseUC dnaSequenceResponseUC) {
        return ApiResponseDnaSequence.builder()
                .mutant(dnaSequenceResponseUC.isMutant())
                .build();
    }

    public ResponseEntity<ApiResponseDnaSequence> apiResponseDnaSequenceResponseEntity(ApiResponseDnaSequence apiResponseDnaSequence) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(apiResponseDnaSequence);
    }
}
