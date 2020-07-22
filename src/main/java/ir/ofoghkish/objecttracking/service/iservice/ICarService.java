package ir.ofoghkish.objecttracking.service.iservice;

import ir.ofoghkish.objecttracking.service.dto.CarDTO;

import java.util.List;

public interface ICarService {
    CarDTO.Info get(Long id);

    List<CarDTO.Info> list();

    CarDTO.Info create(CarDTO.Create request);

    CarDTO.Info update(CarDTO.Update request);

    void delete(Long id);
}
