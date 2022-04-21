package pl.coderslab.manageinspections.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @NotNull
    @NotEmpty
    @Length(min = 4, message = "Provide at least 4 characters.")
    @NotEmpty(message = "Provide a user name.")
    private String username;

    @NotNull
    @Length(min = 8, message = "Create a password using at least 8 characters.")
    @NotEmpty(message = "You must choose a password.")
    private String password;


}
