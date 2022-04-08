package pl.coderslab.manageinspections.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScaffoldDto {
    @Size(min = 5)
    private String name;
    @Size(min = 5)
    private String erectorName;
    @Size(min = 5)
    private String foremanName;
    @Min(value = 1, message = "Choose a difficulty and complexity category from 1 to 4")
    @Max(value = 4, message = "Choose a difficulty and complexity category from 1 to 4")
    private int scaffoldGrade = 4;
    private String area;
    @NotNull
    private String dateOfErection;

    @Size(min = 5)
    @Column(unique = true)
    private String scaffoldId;


}