package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.entity.Coordination;
import ir.ofoghkish.objecttracking.repository.CoordinationDAO;
import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;
import ir.ofoghkish.objecttracking.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoordinationService implements ICoordinationService {

    private final ModelMapper modelMapper;
    private final CoordinationDAO coordinationDAO;

    @Override
    public CoordinationDTO.Info create(CoordinationDTO.Create request) {
        final Coordination coordination = modelMapper.map(request, Coordination.class);
        return save(coordination);
    }

    @Override
    public CoordinationDTO.Info update(CoordinationDTO.Update request) {
        final Optional<Coordination> byId = coordinationDAO.findById(request.getId());
        final Coordination coordination = byId.orElseThrow(() -> new NotFoundException(Coordination.class));

        Coordination updating = new Coordination();
        modelMapper.map(coordination, updating);
        modelMapper.map(request, updating);

        return save(updating);
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
