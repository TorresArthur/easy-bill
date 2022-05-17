package br.com.alura.easybill.easybill.dto;

import br.com.alura.easybill.easybill.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ShowProduct {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String classeFiscal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao.length() < 250){
            this.descricao = descricao;
        }else{
           this.descricao = descricao.substring(0, 250) + "...";
        }
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
       this.preco = preco;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }


    public static ShowProduct toShowProduct(Product product){
        ShowProduct productList = new ShowProduct();
        productList.setId(product.getId());
        productList.setNome(product.getNome());
        productList.setClasseFiscal(product.getClasseFiscal());
        productList.setDescricao(product.getDescricao());
        productList.setPreco(product.getPrecoFinal());
        return productList;
    }

    public static List<ShowProduct> toShowProductList(List<Product> produtos){

       return produtos.stream().map(product -> {
            ShowProduct showProduct = new ShowProduct();
            showProduct.setId(product.getId());
            showProduct.setNome(product.getNome());
            showProduct.setClasseFiscal(product.getClasseFiscal());
            showProduct.setDescricao(product.getDescricao());
            showProduct.setPreco(product.getPrecoFinal());
            return showProduct;
        }).collect(Collectors.toList());
    }
}
