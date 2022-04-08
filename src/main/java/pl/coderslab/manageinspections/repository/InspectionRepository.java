package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Inspection;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {
}
