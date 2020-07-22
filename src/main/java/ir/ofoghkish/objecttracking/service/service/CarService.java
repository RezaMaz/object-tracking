package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.entity.Car;
import ir.ofoghkish.objecttracking.repository.CarDAO;
import ir.ofoghkish.objecttracking.service.dto.CarDTO;
import ir.ofoghkish.objecttracking.service.exception.NotFoundException;
import ir.ofoghkish.objecttracking.service.iservice.ICarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService implements ICarService {

    private final ModelMapper modelMapper;
    private final CarDAO carDAO;

    @Transactional(readOnly = true)
    @Override
    public CarDTO.Info get(Long id) {
        final Optional<Car> byId = carDAO.findById(id);
        final Car car = byId.orElseThrow(() -> new NotFoundException(Car.class));
        return modelMapper.map(car, CarDTO.Info.class);
    }

    @GetMapping
    @Override
    public List<CarDTO.Info> list() {
        final List<Car> all = carDAO.findAll();
        return modelMapper.map(all, new TypeToken<List<CarDTO.Info>>() {
        }.getType());
    }

    @Transactional
    @Override
    public CarDTO.Info create(CarDTO.Create request) {
        final Car car = modelMapper.map(request, Car.class);
        return save(car);
    }

    @Transactional
    @Override
    public CarDTO.Info update(CarDTO.Update request) {
        final Optional<Car> byId = carDAO.findById(request.getId());
        final Car car = byId.orElseThrow(() -> new NotFoundException(Car.class));

        Car updating = new Car();
        modelMapper.map(car, updating);
        modelMapper.map(request, updating);

        return save(updating);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        carDAO.deleteById(id);
    }

    private CarDTO.Info save(Car car) {
        final Car saved = carDAO.saveAndFlush(car);
        return modelMapper.map(saved, CarDTO.Info.class);
    }

}
