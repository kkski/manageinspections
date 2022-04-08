package pl.coderslab.manageinspections.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "areas")
@Data
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Site site;
    private String name;
    @OneToMany
    private List<Scaffold> scaffoldList;
}
