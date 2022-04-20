package pl.coderslab.manageinspections.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sites")
@Getter
@Setter
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 5)
    private String name;
    @Size(min = 5)
    private String address;
    @OneToMany
    private List<Area> areasList = new ArrayList<>();
    @ManyToMany
    private List<Inspector> inspectorsList = new ArrayList<>();

    @OneToMany
    private List<Scaffold> scaffoldList;
    @OneToMany
    private List<Inspection> inspectionList;
}
