package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
}
