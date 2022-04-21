package pl.coderslab.manageinspections.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
public class SiteDto {

    @Size(min = 8, message = "Choose a name using at least 8 characters.")
    @NotEmpty(message = "You must choose a name.")
    @NotNull
    private String name;

    @Size(min = 10, message = "Choose an address using at least 10 characters.")
    @NotEmpty(message = "You must choose an address.")
    @NotNull
    private String address;
}
