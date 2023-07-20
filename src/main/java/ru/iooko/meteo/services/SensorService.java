package ru.iooko.meteo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.meteo.dto.SensorDTO;
import ru.iooko.meteo.models.Sensor;
import ru.iooko.meteo.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Sensor> findByName(String sensorName) {
        return sensorRepository.findByName(sensorName);
    }

    @Transactional
    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
