package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.entity.Coordination;
import ir.ofoghkish.objecttracking.repository.CoordinationDAO;
import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;
import ir.ofoghkish.objecttracking.service.exception.NotFoundException;
import ir.ofoghkish.objecttracking.service.iservice.ICoordinationService;
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
public class CoordinationService implements ICoordinationService {

    private final ModelMapper modelMapper;
    private final CoordinationDAO coordinationDAO;

    @Transactional(readOnly = true)
    @Override
    public CoordinationDTO.Info get(Long id) {
        final Optional<Coordination> byId = coordinationDAO.findById(id);
        final Coordination coordination = byId.orElseThrow(() -> new NotFoundException(Coordination.class));
        return modelMapper.map(coordination, CoordinationDTO.Info.class);
    }

    @GetMapping
    @Override
    public List<CoordinationDTO.Info> list() {
        final List<Coordination> all = coordinationDAO.findAll();
        return modelMapper.map(all, new TypeToken<List<CoordinationDTO.Info>>() {
        }.getType());
    }

    @Override
    public CoordinationDTO.Info create(CoordinationDTO.Create request) {
        final Coordination coordination = modelMapper.map(request, Coordination.class);
        return save(coordination);
    }

    @Override
    public void delete(Long id) {
        coordinationDAO.deleteById(id);
    }

    private CoordinationDTO.Info save(Coordination coordination) {
        final Coordination saved = coordinationDAO.saveAndFlush(coordination);
        return modelMapper.map(saved, CoordinationDTO.Info.class);
    }

}
