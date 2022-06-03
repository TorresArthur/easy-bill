package br.com.alura.easybill.easybill.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {
    private String email;
    private String senha;

    public void setEmail(){
        this.email = email;
    }

    public void setSenha(){
        this.senha = senha;
    }

    public String getEmail(){
        return email;
    }

    public String getSenha(){
        return senha;
    }

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
