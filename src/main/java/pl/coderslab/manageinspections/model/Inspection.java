package pl.coderslab.manageinspections.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="inspections")
@Data
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Scaffold scaffold;
    private LocalDate dateOfInspection;
    private LocalDateTime dateOfCreation;
    @ManyToOne
    private Inspector inspector;
    private String inspectionMessage = "No message";
    private boolean approved = false;

}
