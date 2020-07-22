package ir.ofoghkish.objecttracking.repository;

import ir.ofoghkish.objecttracking.entity.Coordination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinationDAO extends JpaRepository<Coordination, Long> {
}
