package br.com.alura.easybill.easybill.dto;

public class TokenResponse {
    String token;
    String tipo;

    public TokenResponse(String token, String tipo) {
        this.tipo = tipo;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
