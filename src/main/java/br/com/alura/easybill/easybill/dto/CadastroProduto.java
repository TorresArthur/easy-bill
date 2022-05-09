package br.com.alura.easybill.easybill.dto;

import br.com.alura.easybill.easybill.model.Product;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CadastroProduto {
    @NotBlank
    @Size(max = 150)
    private String nome;
    @NotBlank
    @Size(max = 500)
    private String urlImagem;
    @Size(max = 1000)
    private String descricao;
    @NotNull @Positive
    private  BigDecimal preco;
    @Positive
    private BigDecimal precoPromocional;
    @Pattern(regexp= "[0-9]{4}[\\.][0-9]{2}[\\.][0-9]{2}")
    private String classeFiscal;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }

    public Product toProduto(){
        Product product = new Product();
        product.setClasseFiscal(classeFiscal);
        product.setDescricao(descricao);
        product.setPreco(preco);
        product.setNome(nome);
        product.setPrecoPromocional(precoPromocional);
        product.setUrlImagem(urlImagem);
        return product;
    }
}
