package application.controller;

import application.config.exception.CustomException;
import application.constant.AppConstant;
import application.dto.authentication.AuthenticationDTO;
import application.dto.authentication.AuthenticationResponseDTO;
import application.service.authentication.AuthenticationService;
import application.service.authentication.AuthenticationServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.PREFIX_API+"authentication")
public class AuthenticationController {
    private final AuthenticationServiceImp authenticationService;
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationDTO authenticationDTO) {

        if (authenticationDTO.getUsername() == null || authenticationDTO.getPassword() == null) {
            throw  new CustomException(409,"---Not null please check");
        }

//        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.signIn(authenticationDTO);

        return ResponseEntity.ok(null);
    }
}
