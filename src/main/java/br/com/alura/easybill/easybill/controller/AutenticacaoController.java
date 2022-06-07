package br.com.alura.easybill.easybill.controller;


import br.com.alura.easybill.easybill.dto.LoginRequest;
import br.com.alura.easybill.easybill.dto.TokenResponse;
import br.com.alura.easybill.easybill.config.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {


    private final TokenService tokenService;
    //@Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    public AutenticacaoController(TokenService tokenService, AuthenticationManager authenticationManagerBean){
        this.authenticationManager = authenticationManagerBean;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> autentica(@RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.geraToken(authenticate);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));

        } catch(AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
