package ir.ofoghkish.objecttracking.repository;

import ir.ofoghkish.objecttracking.entity.Car;
import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDAO extends JpaRepository<Car, Long> {
    List<Car> findAllByType(CarType type);
}
