package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Scaffold;

import java.util.List;

public interface ScaffoldRepository extends JpaRepository<Scaffold, Long> {
    List<Scaffold> findAllByScaffoldGrade(int scaffoldGrade);
}
