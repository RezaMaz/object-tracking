package ir.ofoghkish.objecttracking.controller;

import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;
import ir.ofoghkish.objecttracking.service.service.ICoordinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/coordination")
public class CoordinationController {

    private final ICoordinationService iCoordinationService;

    @GetMapping(value = "/{id}")
    ResponseEntity<CoordinationDTO.Info> get(@PathVariable Long id) {
        return new ResponseEntity<>(iCoordinationService.get(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    ResponseEntity<List<CoordinationDTO.Info>> list() {
        return new ResponseEntity<>(iCoordinationService.list(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CoordinationDTO.Info> create(@RequestBody CoordinationDTO.Create request) {
        return new ResponseEntity<>(iCoordinationService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        iCoordinationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
