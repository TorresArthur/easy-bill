package br.com.alura.easybill.easybill.model;

import br.com.alura.easybill.easybill.dto.produto.CadastroResponse;
import br.com.alura.easybill.easybill.repository.ProductRepository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String urlImagem;

    @Column(length = 1337)
    private String descricao;
    private BigDecimal preco;
    private BigDecimal precoPromocional;
    private String classeFiscal;

    public Long getId(){
        return id;
    }

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

    public BigDecimal getPrecoFinal() {
        if(precoPromocional == null){
            return preco;
        }else{
            return precoPromocional;
        }
    }

    public BigDecimal getPreco(){
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



    public void atualizar(Long id, ProductRepository productRepository){
       Product product = productRepository.findById(id).get();
       product.setNome(this.nome);
       product.setUrlImagem(this.urlImagem);
       product.setPreco(this.preco);
       product.setClasseFiscal(this.classeFiscal);
       product.setPrecoPromocional(this.precoPromocional);
       product.setDescricao(this.descricao);
       productRepository.save(product);
    }


    public CadastroResponse toCadastroResponse() {
        CadastroResponse cadastroResponse = new CadastroResponse();
        cadastroResponse.setClasseFiscal(classeFiscal);
        cadastroResponse.setPreco(getPrecoFinal());
        cadastroResponse.setUrlImagem(urlImagem);
        cadastroResponse.setNome(nome);
        cadastroResponse.setDescricao(descricao);
        return cadastroResponse;
    }

}
