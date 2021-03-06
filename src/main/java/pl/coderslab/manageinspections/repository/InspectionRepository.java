package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Inspection;

import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {


    Inspection getFirstByScaffoldId(Long scaffId);

    Inspection getFirstByScaffoldIdOrderByDateOfInspectionDesc(Long scaffId);
    List<Inspection> getAllByScaffoldId(Long scaffId);
    List<Inspection> getALlBySiteId(Long siteId);
    Inspection getFirstBySiteId(Long siteId);
}

