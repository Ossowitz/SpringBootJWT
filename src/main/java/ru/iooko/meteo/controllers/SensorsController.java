package ru.iooko.meteo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iooko.meteo.dto.SensorDTO;
import ru.iooko.meteo.models.Sensor;
import ru.iooko.meteo.services.SensorService;
import ru.iooko.meteo.util.exception.MeasurementErrorResponse;
import ru.iooko.meteo.util.exception.MeasurementException;
import ru.iooko.meteo.util.sensorValidator.SensorValidator;

import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static ru.iooko.meteo.util.exception.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        Sensor sensorToAdd = sensorService.convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        sensorService.register(sensorToAdd);
        return ResponseEntity.ok(OK);
    }

    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException exception) {
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(
                exception.getMessage(), currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}
