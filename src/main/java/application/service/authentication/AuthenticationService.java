package application.service.authentication;

import application.dto.authentication.AuthenticationDTO;
import application.dto.authentication.AuthenticationResponseDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO signIn(AuthenticationDTO authenticationDTO);
    AuthenticationResponseDTO signUp(AuthenticationDTO authenticationDTO);
    void signOut();

}
