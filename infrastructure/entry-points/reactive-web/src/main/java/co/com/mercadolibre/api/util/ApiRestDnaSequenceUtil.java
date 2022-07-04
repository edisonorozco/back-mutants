package co.com.mercadolibre.api.util;

import co.com.mercadolibre.api.request.ApiRequestDnaSequence;
import co.com.mercadolibre.api.response.ApiResponseDnaSequence;
import co.com.mercadolibre.api.response.ApiResponseStats;
import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import co.com.mercadolibre.model.specie.response.StatsUC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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

        if (!apiResponseDnaSequence.isMutant())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return ResponseEntity.ok().headers(new HttpHeaders()).body(apiResponseDnaSequence);
    }

    public ResponseEntity<ApiResponseStats> statsUCToEntityApiResponseStat(StatsUC statsUC) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(ApiResponseStats.builder()
                .countMutantDna(statsUC.getCountMutantDna())
                .countHumanDna(statsUC.getCountHumanDna())
                .ratio(formatRatio(statsUC.getRatio()))
                .build());
    }

    private float formatRatio(float ratio) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return Float.parseFloat(decimalFormat.format(ratio));
    }

}
