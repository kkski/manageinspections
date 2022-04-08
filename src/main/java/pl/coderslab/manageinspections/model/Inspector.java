package pl.coderslab.manageinspections.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "inspectors")
@Data
public class Inspector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName, lastName;
    @Min(1)
    @Max(4)
    private int inspectorGrade = 4;


}
