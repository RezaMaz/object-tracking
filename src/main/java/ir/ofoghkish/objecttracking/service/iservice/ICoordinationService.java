package ir.ofoghkish.objecttracking.service.iservice;

import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;

import java.util.List;

public interface ICoordinationService {
    CoordinationDTO.Info get(Long id);

    List<CoordinationDTO.Info> list();

    CoordinationDTO.Info create(CoordinationDTO.Create request);

    void delete(Long id);
}
