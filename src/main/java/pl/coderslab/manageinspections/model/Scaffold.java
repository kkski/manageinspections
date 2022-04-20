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


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name="scaffolds")
@Getter
@Setter
@NoArgsConstructor

public class Scaffold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 5)
    private String name;
    @Size(min = 5)
    private String erectorName;
    @Size(min = 5)
    private String foremanName;
    @Min(value = 1, message = "Choose a difficulty and complexity category from 1 to 4")
    @Max(value = 4, message = "Choose a difficulty and complexity category from 1 to 4")
    private int scaffoldGrade = 4;
    @ManyToOne
    @NotNull
    private Area area;
    @NotNull
    @DateTimeFormat
    private LocalDate dateOfErection;
    @OneToMany
    private List<Inspection> inspectionsList = new ArrayList<Inspection>();
    @Size(min = 5)
    @Column(unique = true)
    private String scaffoldId;
    @ManyToOne
    private Site site;
    private boolean approval = false;

//    @PrePersist
//    @PreUpdate
//    public void checkApproval() {
//        this.inspectionsList.sort(Comparator.comparing(o -> o.getDateOfInspection()));
//        if (!inspectionsList.isEmpty()) {
//            Inspection latestInspection = this.inspectionsList.get(0);
//            if (latestInspection.getDateOfInspection().isAfter(LocalDate.now().minusDays(7)) && latestInspection.isApproved() == true) {
//                this.setApproval(true);
//            }
//        } else {
//            this.setApproval(false);
//        }
//    };

}
