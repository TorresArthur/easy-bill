package br.com.alura.easybill.easybill.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itemvenda")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private String observacao;
    private BigDecimal precoUnitario;
    private BigDecimal getPrecoUnitarioPromocional;
    @ManyToOne
    private Venda venda;
    @ManyToOne
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getGetPrecoUnitarioPromocional() {
        return getPrecoUnitarioPromocional;
    }

    public void setGetPrecoUnitarioPromocional(BigDecimal getPrecoUnitarioPromocional) {
        this.getPrecoUnitarioPromocional = getPrecoUnitarioPromocional;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}


