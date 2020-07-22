package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.entity.Car;
import ir.ofoghkish.objecttracking.repository.CarDAO;
import ir.ofoghkish.objecttracking.service.dto.CarDTO;
import ir.ofoghkish.objecttracking.service.exception.NotFoundException;
import ir.ofoghkish.objecttracking.service.iservice.ICarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService implements ICarService {

    private final ModelMapper modelMapper;
    private final CarDAO carDAO;

    @Override
    public CarDTO.Info create(CarDTO.Create request) {
        final Car car = modelMapper.map(request, Car.class);
        return save(car);
    }

    @Override
    public CarDTO.Info update(CarDTO.Update request) {
        final Optional<Car> byId = carDAO.findById(request.getId());
        final Car car = byId.orElseThrow(() -> new NotFoundException(Car.class));

        Car updating = new Car();
        modelMapper.map(car, updating);
        modelMapper.map(request, updating);

        return save(updating);
    }

    @Override
    public void delete(Long id) {
        carDAO.deleteById(id);
    }

    private CarDTO.Info save(Car car) {
        final Car saved = carDAO.saveAndFlush(car);
        return modelMapper.map(saved, CarDTO.Info.class);
    }

}
