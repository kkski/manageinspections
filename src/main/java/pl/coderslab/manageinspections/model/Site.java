package pl.coderslab.manageinspections.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sites")
@Data
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

    @OneToOne
    @JoinColumn(name = "chosen_area_id")
    private Area chosenArea;
    @OneToOne
    @JoinColumn(name = "chosen_scaffold_id")
    private Scaffold chosenScaffold;
    @OneToMany
    private List<Scaffold> scaffoldList;
}
