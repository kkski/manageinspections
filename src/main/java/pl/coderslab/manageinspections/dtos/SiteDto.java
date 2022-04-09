package pl.coderslab.manageinspections.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
public class SiteDto {
    @Size(min = 5)
    private String name;
    @Size(min = 5)
    private String address;
    @Size(min = 5)
    private String scaffold;
}
