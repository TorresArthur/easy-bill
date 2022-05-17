package br.com.alura.easybill.easybill.dto;

public class MensagemErro {

    private String campoErro;
    private String mensagemErro;

    public MensagemErro(String campoErro, String mensagemErro){
        this.campoErro = campoErro;
        this.mensagemErro = mensagemErro;
    }

    public String getCampoErro() {
        return campoErro;
    }

    public void setCampoErro(String campoErro) {
        this.campoErro = campoErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
