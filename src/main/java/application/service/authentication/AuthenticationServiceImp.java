package application.service.authentication;

import application.config.exception.CustomException;
import application.dto.authentication.AuthenticationDTO;
import application.dto.authentication.AuthenticationResponseDTO;
import authentication.proto.AuthenticationRequestSimple;
import authentication.proto.AuthenticationResponseSimple;
import authentication.proto.AuthenticationSimpleGrpc;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImp implements  AuthenticationService{
    private final AuthenticationSimpleGrpc.AuthenticationSimpleBlockingStub stub;
    @Override
    public AuthenticationResponseDTO signIn(AuthenticationDTO authenticationDTO) {

        if(authenticationDTO==null||authenticationDTO.getUsername()==null||authenticationDTO.getPassword()==null){
            throw new CustomException(409,"Validate failed");
        }

        AuthenticationRequestSimple authenticationRequestSimple=
                AuthenticationRequestSimple.newBuilder()
                .setUsername(authenticationDTO.getUsername())
                .setPassword(authenticationDTO.getPassword()).build();

        AuthenticationResponseSimple authenticationResponseSimple=stub.signIn(authenticationRequestSimple);
        if(authenticationResponseSimple==null){
            throw new CustomException(409,"Validate failed");
        }

        AuthenticationResponseDTO authenticationResponseDTO=new AuthenticationResponseDTO();
        authenticationResponseDTO.setToken(authenticationResponseSimple.getToken());
        authenticationResponseDTO.setUsername(authenticationResponseSimple.getUsername());

        return authenticationResponseDTO;
    }

    @Override
    public AuthenticationResponseDTO signUp(AuthenticationDTO authenticationDTO) {
        return null;
    }

    @Override
    public void signOut() {

    }
}
