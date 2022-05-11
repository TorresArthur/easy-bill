package br.com.alura.easybill.easybill.dto;

import br.com.alura.easybill.easybill.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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


    public static ShowProduct oneProductList(Product product){
        ShowProduct productList = new ShowProduct();
        productList.setId(product.getId());
        productList.setNome(product.getNome());
        productList.setClasseFiscal(product.getClasseFiscal());
        productList.setDescricao(product.getDescricao());
        productList.setPreco(product.getPrecoFinal());
        return productList;
    }

    public static List<ShowProduct> toProductList(List<Product> lista){
        //lista.stream().map().collect(Collectors.toList());
        List<ShowProduct> listaForm = new ArrayList<>();
        for (Product product : lista){
            ShowProduct productList = new ShowProduct();
            productList.setId(product.getId());
            productList.setNome(product.getNome());
            productList.setClasseFiscal(product.getClasseFiscal());
            productList.setDescricao(product.getDescricao());
            productList.setPreco(product.getPrecoFinal());
            listaForm.add(productList);
        }
        return listaForm;
    }


}
