package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.entity.Car;
import ir.ofoghkish.objecttracking.entity.Coordination;
import ir.ofoghkish.objecttracking.repository.CarDAO;
import ir.ofoghkish.objecttracking.service.dto.CarDTO;
import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;
import ir.ofoghkish.objecttracking.service.exception.NotFoundException;
import ir.ofoghkish.objecttracking.service.iservice.ICarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
@Service
public class CarService implements ICarService {

    private final ModelMapper modelMapper;
    private final CarDAO carDAO;
    private final ICoordinationService iCoordinationService;

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

        car.setCoordinations(null);
        CarDTO.Info saved = save(car);

        request.getCoordinations().forEach(q -> {
            q.setCarId(saved.getId());
            iCoordinationService.create(modelMapper.map(q, CoordinationDTO.Create.class));
        });
        return saved;
    }

    @Transactional
    @Override
    public CarDTO.Info update(CarDTO.Update request) {
        final Optional<Car> byId = carDAO.findById(request.getId());
        final Car car = byId.orElseThrow(() -> new NotFoundException(Car.class));

        Car updating = new Car();
        modelMapper.map(car, updating);
        modelMapper.map(request, updating);

        updating.setCoordinations(null);
        CarDTO.Info saved = save(updating);

        request.getCoordinations().forEach(q -> {
            if (!isCoordinationOutlier(car.getCoordinations(), q.getLongitude(), q.getLongitude()))
                iCoordinationService.create(modelMapper.map(q, CoordinationDTO.Create.class));
        });
        return saved;
    }

    private Boolean isCoordinationOutlier(List<Coordination> coordinations, BigDecimal longitude, BigDecimal longitude1) {
        return false;
    }

    List<CoordinationDTO.Info> getReducedPath(Long id) {
        List<CoordinationDTO.Info> coordinations = get(id).getCoordinations();
        return coordinations;
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

    private Boolean isIntersection(Long firstId, Long secondId) {
        CarDTO.Info firstCar = get(firstId);
        CarDTO.Info secondCar = get(secondId);
        List<CoordinationDTO.Info> firstCarCoordinations = firstCar.getCoordinations();
        List<CoordinationDTO.Info> secondCarCoordinations = secondCar.getCoordinations();
        AtomicBoolean intersection = new AtomicBoolean(false);
        firstCarCoordinations.forEach(q -> secondCarCoordinations.forEach(x -> {
            if (q.getLatitude().equals(x.getLatitude()) && q.getLongitude().equals(x.getLongitude()))
                intersection.set(true);
        }));
        return intersection.get();
    }

}
