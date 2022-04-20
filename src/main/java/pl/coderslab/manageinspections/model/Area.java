package pl.coderslab.manageinspections.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "areas")
@Getter
@Setter
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Site site;
    private String name;
    @OneToMany
            (
                    mappedBy = "area",
                    cascade = CascadeType.ALL,
                    orphanRemoval = true
            )
    private List<Scaffold> scaffoldList;
}
