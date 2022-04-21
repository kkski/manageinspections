package pl.coderslab.manageinspections.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 2, message = "Provide a name, minimum 2 characters.")
    @NotEmpty(message = "You must provide a name.")
    @NotNull(message = "You must provide a name.")
    private String name;
    @OneToMany
            (
                    mappedBy = "area",
                    cascade = CascadeType.ALL,
                    orphanRemoval = true
            )
    private List<Scaffold> scaffoldList;
}
