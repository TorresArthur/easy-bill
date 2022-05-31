package br.com.alura.easybill.easybill.dto.venda;

import br.com.alura.easybill.easybill.model.ItemVenda;
import br.com.alura.easybill.easybill.model.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaResponse {
    private Long id;
    private String dataRealizacao;
    private Long clienteId;
    private List<ItemVendaResponse> itemResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(String dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVendaResponse> getItemResponse() {
        return itemResponse;
    }

    public void setItemResponse(List<ItemVendaResponse> itemResponse) {
        this.itemResponse = itemResponse;
    }

    public void fromVenda(Venda venda, List<ItemVenda> itens) {

        setClienteId(venda.getCliente().getId());
        setDataRealizacao(venda.getDataVenda().toString());
        setId(venda.getId());


        List<ItemVendaResponse> listaResponse = new ArrayList<>();
        itens.forEach(item -> {
            ItemVendaResponse itemVendaResponse = new ItemVendaResponse();
            itemVendaResponse.fromItemVenda(item);
            listaResponse.add(itemVendaResponse);
        });

        setItemResponse(listaResponse);
    }
}
