package pl.coderslab.manageinspections.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.manageinspections.model.Area;
import pl.coderslab.manageinspections.model.Scaffold;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    @Query("select a from Area a where a.site.id = ?1")
    List<Area> getAllBySiteId(Long id);
    Area getAreaByNameAndSiteId(String name, Long id);
    Area getAreaByScaffoldListContains(Scaffold scaffold);
}
