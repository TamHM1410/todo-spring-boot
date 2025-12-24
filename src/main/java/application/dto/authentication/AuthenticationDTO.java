package application.dto.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationDTO {
    //sign in
    private String username;
    private String password;

    //sign up
    private String email;


}
