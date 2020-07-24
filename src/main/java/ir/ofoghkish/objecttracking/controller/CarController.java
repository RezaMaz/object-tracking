package ir.ofoghkish.objecttracking.controller;

import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import ir.ofoghkish.objecttracking.service.dto.CarDTO;
import ir.ofoghkish.objecttracking.service.iservice.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/car")
public class CarController {

    private final ICarService iCarService;

    @GetMapping(value = "/{id}")
    ResponseEntity<CarDTO.Info> get(@PathVariable Long id) {
        return new ResponseEntity<>(iCarService.get(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    ResponseEntity<List<CarDTO.Info>> list() {
        return new ResponseEntity<>(iCarService.list(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CarDTO.Info> create(@RequestBody CarDTO.Create request) {
        return new ResponseEntity<>(iCarService.create(request), HttpStatus.CREATED);
    }

    @PutMapping()
    ResponseEntity<CarDTO.Info> update(@RequestBody CarDTO.Update request) {
        return new ResponseEntity<>(iCarService.update(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        iCarService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/interference/{firstId}/{secondId}")
    ResponseEntity<Boolean> isInterference(@PathVariable Long firstId, @PathVariable Long secondId) {
        return new ResponseEntity<>(iCarService.isInterference(iCarService.get(firstId), iCarService.get(secondId)), HttpStatus.OK);
    }

    @GetMapping(value = "/search-by-type")
    ResponseEntity<List<CarDTO.Info>> list(@RequestParam CarType type) {
        return new ResponseEntity<>(iCarService.findCarsByType(type), HttpStatus.OK);
    }

}
