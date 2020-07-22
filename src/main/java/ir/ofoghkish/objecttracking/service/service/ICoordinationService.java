package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;

public interface ICoordinationService {
    CoordinationDTO.Info create(CoordinationDTO.Create request);

    CoordinationDTO.Info update(CoordinationDTO.Update request);

    void delete(Long id);
}
