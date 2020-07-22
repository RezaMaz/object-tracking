package ir.ofoghkish.objecttracking.service.iservice;

import ir.ofoghkish.objecttracking.service.dto.CarDTO;

public interface ICarService {
    CarDTO.Info create(CarDTO.Create request);

    CarDTO.Info update(CarDTO.Update request);

    void delete(Long id);
}
