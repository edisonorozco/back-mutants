package co.com.mercadolibre.api;

import co.com.mercadolibre.api.common.ApiRoute;
import co.com.mercadolibre.api.response.ApiResponseStats;
import co.com.mercadolibre.api.util.ApiRestDnaSequenceUtil;
import co.com.mercadolibre.usecase.dnasequence.DnaSequenceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = ApiRoute.PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiRestStat {

    private final DnaSequenceUseCase dnaSequenceUseCase;
    private final ApiRestDnaSequenceUtil apiRestDnaSequenceUtil = new ApiRestDnaSequenceUtil();

    @GetMapping(path = ApiRoute.PATH_RETRIEVE_STATS)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<ApiResponseStats>> retrieve() {
        return dnaSequenceUseCase
                .getAllSpecies()
                .map(apiRestDnaSequenceUtil::statsUCToEntityApiResponseStat);
    }
}
