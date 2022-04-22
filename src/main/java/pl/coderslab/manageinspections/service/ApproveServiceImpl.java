package pl.coderslab.manageinspections.service;

import org.springframework.stereotype.Service;
import pl.coderslab.manageinspections.model.Inspection;
import pl.coderslab.manageinspections.model.Scaffold;
import pl.coderslab.manageinspections.repository.InspectionRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class ApproveServiceImpl implements ApproveService {
    @Override
    public boolean isApproved(Scaffold scaff) {
        //check if there are any inspections
        List<Inspection> inspectionList = scaff.getInspectionsList();
        if (inspectionList.size() == 0) {
            return false;
        } else {
            inspectionList.sort(Comparator.comparing(Inspection::getDateOfInspection));
            Collections.reverse(inspectionList);
            Inspection lastInspection = inspectionList.get(0);

            if (!lastInspection.isApproved()) {
                return false;
            }
            return !lastInspection.getDateOfInspection().isBefore(LocalDate.now().minusDays(7));
        }

    }
}
