package pl.coderslab.manageinspections.model;

import lombok.Data;

import javax.persistence.*;

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
}
