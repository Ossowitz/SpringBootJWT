package ru.iooko.meteo.util.sensorValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.iooko.meteo.models.Sensor;
import ru.iooko.meteo.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        validateName(sensor, errors);
    }

    private void validateName(Sensor sensor, Errors errors) {
        if (sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name",  "This sensor will be taken!");
        }
    }
}
