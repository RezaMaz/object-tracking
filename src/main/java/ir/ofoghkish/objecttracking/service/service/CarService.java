package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.entity.Car;
import ir.ofoghkish.objecttracking.entity.Coordination;
import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        if (request.getCoordinations() != null && request.getCoordinations().size() > 0) {
            request.getCoordinations().forEach(q -> {
                q.setCarId(saved.getId());
                iCoordinationService.create(modelMapper.map(q, CoordinationDTO.Create.class));
            });
        }

        return saved;
    }

    @Transactional
    @Override
    public CarDTO.Info update(CarDTO.Update request) {
        final Optional<Car> byId = carDAO.findById(request.getId());
        final Car car = byId.orElseThrow(() -> new NotFoundException(Car.class));

        car.setCoordinations(null);

        Car updating = new Car();
        modelMapper.map(car, updating);
        modelMapper.map(request, updating);

        CarDTO.Info saved = save(updating);

        if (request.getCoordinations() != null && request.getCoordinations().size() > 0) {
            request.getCoordinations().forEach(q -> {
                if (!isCoordinationOutlier(car.getCoordinations(), q.getLatitude(), q.getLongitude())) {
                    q.setCarId(saved.getId());
                    iCoordinationService.create(modelMapper.map(q, CoordinationDTO.Create.class));
                }
            });
        }

        return saved;
    }

    private Boolean isCoordinationOutlier(List<Coordination> coordinations, BigDecimal latitude, BigDecimal longitude) {
        if (coordinations.size() <= 1)
            return false;

        double distanceTotal = 0;
        for (int i = 0; i < coordinations.size() - 1; i++) {
            Coordination a = coordinations.get(i);
            Coordination b = coordinations.get(i + 1);

            double distance = Math.sqrt(Math.pow(a.getLatitude().subtract(b.getLatitude()).doubleValue(), 2) +
                    Math.pow(a.getLongitude().subtract(b.getLongitude()).doubleValue(), 2));
            distanceTotal = distanceTotal + distance;
        }

        Coordination lastCoordination = coordinations.get(coordinations.size() - 1);
        double velocity = distanceTotal * 1000 / (lastCoordination.getCreatedDate().getTime() - coordinations.get(0).getCreatedDate().getTime());

        double newDistance = Math.sqrt(Math.pow(lastCoordination.getLatitude().subtract(latitude).doubleValue(), 2) +
                Math.pow(lastCoordination.getLongitude().subtract(longitude).doubleValue(), 2));
        double newVelocity = newDistance * 1000 / (new Date().getTime() - lastCoordination.getCreatedDate().getTime());

        return newVelocity > velocity * 2;
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

    @Override
    public Boolean isInterference(CarDTO.Info firstCar, CarDTO.Info secondCar) {
        List<CoordinationDTO.Info> firstCarCoordinations = firstCar.getCoordinations();
        List<CoordinationDTO.Info> secondCarCoordinations = secondCar.getCoordinations();

        if (firstCarCoordinations.size() <= 1 || secondCarCoordinations.size() <= 1)
            return false;

        for (int i = 0; i < firstCarCoordinations.size() - 1; i++) {
            CoordinationDTO.Info a = firstCarCoordinations.get(i);
            CoordinationDTO.Info b = firstCarCoordinations.get(i + 1);

            for (int j = 0; j < secondCarCoordinations.size() - 1; j++) {
                CoordinationDTO.Info c = secondCarCoordinations.get(j);
                CoordinationDTO.Info d = secondCarCoordinations.get(j + 1);
                if (Utility.IsIntersecting(a, b, c, d))
                    return true;
            }
        }

        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarDTO.Info> findCarsByType(CarType type) {
        final List<Car> all = carDAO.findAllByType(type);
        return modelMapper.map(all, new TypeToken<List<CarDTO.Info>>() {
        }.getType());
    }
}
