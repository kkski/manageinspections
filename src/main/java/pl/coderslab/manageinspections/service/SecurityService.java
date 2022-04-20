package pl.coderslab.manageinspections.service;

public interface SecurityService {

    public boolean hasAccess(Long userId, Long siteId);

}
