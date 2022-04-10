package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.manageinspections.model.Inspection;

import java.time.LocalDate;
import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {


    Inspection getFirstByScaffoldId(Long scaffId);
    List<Inspection> getAllByScaffoldId(Long scaffId);
}

