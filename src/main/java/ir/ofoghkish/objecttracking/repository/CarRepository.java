package ir.ofoghkish.objecttracking.repository;

import ir.ofoghkish.objecttracking.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
