package pl.coderslab.manageinspections.service;

import org.springframework.stereotype.Service;
import pl.coderslab.manageinspections.model.User;
import pl.coderslab.manageinspections.repository.RoleRepository;
import pl.coderslab.manageinspections.repository.SiteRepository;
import pl.coderslab.manageinspections.repository.UserRepository;
@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final SiteRepository siteRepository;

    public SecurityServiceImpl(UserRepository userRepository, SiteRepository siteRepository) {
        this.userRepository = userRepository;
        this.siteRepository = siteRepository;
    }

    @Override
    public boolean hasAccess(Long userId, Long siteId) {
        User myUser = userRepository.getById(userId);
        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            return true;
        } else {
            return false;
        }
    }
}
