package pl.coderslab.manageinspections.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.manageinspections.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findSiteByName(String name);
}
