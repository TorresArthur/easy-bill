package br.com.alura.easybill.easybill.dto;

import br.com.alura.easybill.easybill.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductList {
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
        this.descricao = descricao;
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

    public static List<ProductList> converte(List<Product> lista){
        //lista.stream().map().collect(Collectors.toList());
        List<ProductList> listaForm = new ArrayList<>();
        for (Product product : lista){
            ProductList productList = new ProductList();
            productList.setId(product.getId());
            productList.setNome(product.getNome());
            productList.setClasseFiscal(product.getClasseFiscal());
            productList.setDescricao(product.getDescricao());
            productList.setPreco(product.getPreco());
            listaForm.add(productList);
        }
        return listaForm;
    }


}
