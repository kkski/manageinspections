package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Area;
import pl.coderslab.manageinspections.model.Scaffold;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Area getAreaByNameAndSiteId(String name, Long id);
    Area getAreaByScaffoldListContains(Scaffold scaffold);
}
