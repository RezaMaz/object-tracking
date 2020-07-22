package ir.ofoghkish.objecttracking.controller;

import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;
import ir.ofoghkish.objecttracking.service.service.ICoordinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/coordination")
public class CoordinationController {

    private ICoordinationService iCoordinationService;

    @PostMapping
    ResponseEntity<CoordinationDTO.Info> create(@RequestBody CoordinationDTO.Create request) {
        return new ResponseEntity<>(iCoordinationService.create(request), HttpStatus.CREATED);
    }

    @PutMapping()
    ResponseEntity<CoordinationDTO.Info> update(@RequestBody CoordinationDTO.Update request) {
        return new ResponseEntity<>(iCoordinationService.update(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity delete(@PathVariable Long id) {
        iCoordinationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
