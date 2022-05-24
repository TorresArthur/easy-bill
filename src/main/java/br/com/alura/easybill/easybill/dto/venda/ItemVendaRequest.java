package br.com.alura.easybill.easybill.dto.venda;

import br.com.alura.easybill.easybill.model.ItemVenda;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Component
public class ItemVendaRequest {
    @Positive @NotNull
    private Integer quantidade;
    private String observacao;
    private Long produtoId;



    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQunatidade(Integer qunatidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public ItemVenda toItemVenda(ProductRepository repository){
        ItemVenda item = new ItemVenda();
            item.setObservacao(observacao);
            item.setQuantidade(quantidade);
            item.setProduct(repository.findById(produtoId).get());
            item.setPrecoUnitario(repository.findById(produtoId).get().getPreco());

        return item;
    }
}
