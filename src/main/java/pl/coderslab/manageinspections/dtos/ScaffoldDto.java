package pl.coderslab.manageinspections.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScaffoldDto {
    @Size(min = 5, message = "Use minimum 5 characters.")
    @NotNull
    @NotEmpty(message = "You must choose a name of scaffold.")
    private String name;

    @Size(min = 5, message = "Use minimum 5 characters.")
    @NotNull
    @NotEmpty(message = "You must choose a name of builder.")

    private String erectorName;


    @Size(min = 5, message = "Use minimum 5 characters.")
    @NotNull
    @NotEmpty(message = "You must choose a name of foreman.")
    private String foremanName;


    @Min(value = 1, message = "Choose a difficulty and complexity category from 1 to 4")
    @Max(value = 4, message = "Choose a difficulty and complexity category from 1 to 4")
    @NotNull(message = "You must choose a grade of scaffolding.")
    @Column(nullable = false)
    private int scaffoldGrade = 4;

    private String area;

    //@PastOrPresent(message ="Wrong date chosen.")
    @NotNull(message = "You must choose a date of finished erection of scaffolding.")
    @NotEmpty(message = "You must choose a date of finished erection of scaffolding.")
    private String dateOfErection;


    @Size(min = 5, message = "Use minimum 5 characters.")
    @NotNull
    @NotEmpty(message = "You must choose an identification sequence of scaffold.")
    private String scaffoldId;


}