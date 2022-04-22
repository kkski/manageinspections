package pl.coderslab.manageinspections.service;

import pl.coderslab.manageinspections.model.Scaffold;

public interface ApproveService {
    boolean isApproved(Scaffold scaff);
}
