package ro.fasttrackit.hotelserver.exception.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionResponse(String internalCode, String message, List<FieldError> fieldErrors) {
}
