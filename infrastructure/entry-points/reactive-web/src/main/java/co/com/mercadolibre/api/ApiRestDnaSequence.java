package co.com.mercadolibre.api;

import co.com.mercadolibre.api.common.ApiRoute;
import co.com.mercadolibre.api.request.ApiRequestDnaSequence;
import co.com.mercadolibre.api.response.ApiResponseDnaSequence;
import co.com.mercadolibre.api.util.ApiRestDnaSequenceUtil;
import co.com.mercadolibre.usecase.dnasequence.DnaSequenceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ApiRoute.PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiRestDnaSequence {

    private final DnaSequenceUseCase dnaSequenceUseCase;
    private final ApiRestDnaSequenceUtil apiRestDnaSequenceUtil = new ApiRestDnaSequenceUtil();

    @PostMapping(path = ApiRoute.PATH_RETRIEVE_MUTANT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<ApiResponseDnaSequence>> retrieve(
            @Valid @RequestBody Mono<ApiRequestDnaSequence> request) {
        return request
                .map(apiRestDnaSequenceUtil::apiRequestToUseCaseRequest)
                .flatMap(dnaSequenceUseCase::isMutant)
                .map(apiRestDnaSequenceUtil::useCaseResponseToApiResponse)
                .map(apiRestDnaSequenceUtil::apiResponseDnaSequenceResponseEntity);
    }

}
