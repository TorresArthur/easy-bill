package br.com.alura.easybill.easybill.dto.venda;

import br.com.alura.easybill.easybill.model.ItemVenda;

import java.math.BigDecimal;

public class ItemVendaResponse {
    private Long id;
    private Integer quantidade;
    private String observacao;
    private BigDecimal precoUnitario;
    private BigDecimal precoUnitarioPromocional;

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

    public BigDecimal getPrecoUnitarioPromocional() {
        return precoUnitarioPromocional;
    }

    public void setPrecoUnitarioPromocional(BigDecimal precoUnitarioPromocional) {
        this.precoUnitarioPromocional = precoUnitarioPromocional;
    }

    public void fromItemVenda(ItemVenda itemVenda){
        setQuantidade(itemVenda.getQuantidade());
        setObservacao(itemVenda.getObservacao());
        setPrecoUnitario(itemVenda.getPrecoUnitario());
        setPrecoUnitarioPromocional(itemVenda.getGetPrecoUnitarioPromocional());
        setId(itemVenda.getId());
    }
}
