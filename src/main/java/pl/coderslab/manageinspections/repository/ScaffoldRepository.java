package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Scaffold;
import pl.coderslab.manageinspections.model.Site;

import java.util.List;

public interface ScaffoldRepository extends JpaRepository<Scaffold, Long> {
    List<Scaffold> getAllByScaffoldGrade(int scaffoldGrade);
    List<Scaffold> getAllBySite(Site site);
}
