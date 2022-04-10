package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.manageinspections.model.Scaffold;
import pl.coderslab.manageinspections.model.Site;

import java.util.List;

public interface ScaffoldRepository extends JpaRepository<Scaffold, Long> {
    List<Scaffold> getAllByScaffoldGrade(int scaffoldGrade);
    @Query("select s from Scaffold s where s.site.id = ?1")
    List<Scaffold> getAllBySiteId(Long id);
}
