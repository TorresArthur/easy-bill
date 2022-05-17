package br.com.alura.easybill.easybill.dto;

import java.math.BigDecimal;

public class CadastroResponse {

    private String nome;
    private BigDecimal preco;
    private String urlImagem;
    private String classeFiscal;
    private String descricao;
    private Long id;



    public Long getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
