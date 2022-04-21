package pl.coderslab.manageinspections.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
public class InspectionDto {
    //@PastOrPresent(message ="Wrong date chosen.")
    @NotNull(message = "You must choose a date of inspection.")
    @NotEmpty(message = "You must choose a date of inspection.")
    private String dateOfInspection;
    @Size(min = 2, message = "Use minimum 2 characters.")
    @NotNull
    @NotEmpty(message = "You must provide a note of inspection.")
    private String inspectionMessage;
    private Boolean approved = false;
}
