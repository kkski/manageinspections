package pl.coderslab.manageinspections.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Inspector;


public interface InspectorRepository extends JpaRepository<Inspector, Long> {
}
