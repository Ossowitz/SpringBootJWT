package ru.iooko.meteo.util.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();

        String errorMsg = errors.stream()
                .map(error -> error.getField() + " - " + (
                        error.getDefaultMessage() == null
                                ? error.getCode()
                                : error.getDefaultMessage()))
                .collect(joining("; "));

        throw new MeasurementException(errorMsg);
    }
}
