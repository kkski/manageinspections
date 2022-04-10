package pl.coderslab.manageinspections.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
public class InspectionDto {
    private String dateOfInspection;
    private String inspectionMessage;
    private Boolean approved;
}
