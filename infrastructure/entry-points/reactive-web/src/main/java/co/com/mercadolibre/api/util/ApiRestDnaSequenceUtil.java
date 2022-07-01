package co.com.mercadolibre.api.util;

import co.com.mercadolibre.api.request.ApiRequestDnaSequence;
import co.com.mercadolibre.api.response.ApiResponseDnaSequence;
import co.com.mercadolibre.api.response.ApiResponseStats;
import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import co.com.mercadolibre.model.specie.response.StatsUC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ApiRestDnaSequenceUtil {

    public DnaSequence apiRequestToUseCaseRequest(ApiRequestDnaSequence apiRequestDnaSequence) {
        return DnaSequence.builder()
                .dna(apiRequestDnaSequence.getDna())
                .build();
    }

    public ApiResponseDnaSequence useCaseResponseToApiResponse(DnaSequenceResponseUC dnaSequenceResponseUC) {
        return ApiResponseDnaSequence.builder()
                .mutant(dnaSequenceResponseUC.isMutant())
                .build();
    }

    public ResponseEntity<ApiResponseDnaSequence> apiResponseDnaSequenceResponseEntity(ApiResponseDnaSequence apiResponseDnaSequence) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(apiResponseDnaSequence);
    }

    public ResponseEntity<ApiResponseStats> statsUCToEntityApiResponseStat(StatsUC statsUC) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(ApiResponseStats.builder()
                .count_mutant_dna(statsUC.getCount_mutant_dna())
                .count_human_dna(statsUC.getCount_human_dna())
                .ratio(statsUC.getRatio())
                .build());
    }
}
