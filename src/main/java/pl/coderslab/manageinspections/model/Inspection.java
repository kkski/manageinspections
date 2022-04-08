package pl.coderslab.manageinspections.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="inspections")
@Data
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   // @ManyToOne
    //private Scaffold scaffold;

    private Date dateOfInspection;
    //@ManyToOne
    //private Inspector inspector;
    private String inspectionMessage = "No message";
    private boolean approved = false;

//    @PrePersist
//    @PreUpdate
//    public void calculateGrade() {
//
//        scaffoldGrade = 7
//
//    }

}
