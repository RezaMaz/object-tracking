package ir.ofoghkish.objecttracking.service.iservice;

import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import ir.ofoghkish.objecttracking.service.dto.CarDTO;

import java.util.List;

public interface ICarService {
    CarDTO.Info get(Long id);

    List<CarDTO.Info> list();

    CarDTO.Info create(CarDTO.Create request);

    CarDTO.Info update(CarDTO.Update request);

    void delete(Long id);

    Boolean isInterference(CarDTO.Info firstCar, CarDTO.Info secondCar);

    List<CarDTO.Info> findCarsByType(CarType type);
}
